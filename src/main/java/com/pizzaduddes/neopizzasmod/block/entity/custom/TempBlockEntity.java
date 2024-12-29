package com.pizzaduddes.neopizzasmod.block.entity.custom;

import com.pizzaduddes.neopizzasmod.block.custom.TempBlock;
import com.pizzaduddes.neopizzasmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TempBlockEntity extends BlockEntity {
    private int value = 0;

    public TempBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TEMP_BE.get(), pos, blockState);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
        updateBlockState();
    }

    public static void tick1(Level level, BlockPos pos, BlockState state, TempBlockEntity blockEntity) {

        if (level.getGameTime() % 20 == 0 && blockEntity.getValue() < 500) {
            blockEntity.setValue(blockEntity.getValue() + 5);
        }

    }

    private void updateBlockState() {
        if (level != null && !level.isClientSide()) {
            int temprange = Math.min(value / 100, 4);
            BlockState state = getBlockState().setValue(TempBlock.TEMP, temprange);
            level.setBlock(worldPosition, state, 3);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.value = tag.getInt("Value");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("Value", this.value);
    }
}
