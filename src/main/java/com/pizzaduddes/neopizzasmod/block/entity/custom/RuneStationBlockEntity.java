package com.pizzaduddes.neopizzasmod.block.entity.custom;
import com.pizzaduddes.neopizzasmod.block.entity.ModBlockEntities;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import com.pizzaduddes.neopizzasmod.screen.custom.RuneStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class RuneStationBlockEntity extends BlockEntity implements MenuProvider {


    public final ItemStackHandler inventoryrs = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public int current_recipe = 0;
    private static final int ENERGY_ITEM_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public RuneStationBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.RUNE_STATION_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> RuneStationBlockEntity.this.progress;
                    case 1 -> RuneStationBlockEntity.this.maxProgress;
                    default ->  0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0: RuneStationBlockEntity.this.progress = i1;
                    case 1: RuneStationBlockEntity.this.maxProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.neopizzasmod.rune_station");

    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new RuneStationMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", inventoryrs.serializeNBT(registries));
        tag.putInt("runestation.progress", progress);
        tag.putInt("runestation.max_progress", maxProgress);

        super.saveAdditional(tag , registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventoryrs.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("runestation.progress");
        maxProgress = tag.getInt("runestation.max_progress");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventoryrs.getSlots());
        for(int i = 0; i < inventoryrs.getSlots(); i++) {
            inv.setItem(i, inventoryrs.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe() && isOutputSlotEmptyOrReceivable()){
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasCraftingFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        ItemStack outputDefault = new ItemStack(ModItems.RUNE_ENERGY_UNFILLED.get());
            inventoryrs.extractItem(INPUT_SLOT, 1, false);
            inventoryrs.setStackInSlot(OUTPUT_SLOT, new ItemStack(outputDefault.getItem(),
                    inventoryrs.getStackInSlot(OUTPUT_SLOT).getCount() + outputDefault.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.inventoryrs.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.inventoryrs.getStackInSlot(OUTPUT_SLOT).getCount() < this.inventoryrs.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        ItemStack input = new ItemStack(ModItems.RUNE_ROCK.get());
        ItemStack output = new ItemStack(ModItems.TANZANITE.get());

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output) &&
                this.inventoryrs.getStackInSlot(INPUT_SLOT).getItem() == input.getItem();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventoryrs.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                inventoryrs.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventoryrs.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : inventoryrs.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventoryrs.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
