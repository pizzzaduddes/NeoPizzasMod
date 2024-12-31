package com.pizzaduddes.neopizzasmod.block.custom;

import com.mojang.serialization.MapCodec;
import com.pizzaduddes.neopizzasmod.block.entity.ModBlockEntities;
import com.pizzaduddes.neopizzasmod.block.entity.custom.TempBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TempBlock extends BaseEntityBlock {
    public static final IntegerProperty TEMP = IntegerProperty.create("temp", 0, 4);
    public static final MapCodec<TempBlock> CODEC = simpleCodec(TempBlock::new);

    public TempBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TEMP, 0));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.TEMP_BE.get(), (level1, blockPos, blockState, blockEntity) -> {
            if (blockEntity instanceof TempBlockEntity tempBlockEntity){
                tempBlockEntity.tick1(level1, blockPos, blockState, blockEntity);
            }
        });
    }
    


    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true ;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof TempBlockEntity tempEntity) {
                if (player instanceof ServerPlayer serverPlayer) {
                    int currentTemp = state.getValue(TEMP);

                    serverPlayer.sendSystemMessage(Component.literal("Temp: " + tempEntity.getValue()));
                    serverPlayer.sendSystemMessage(Component.literal("State: " + currentTemp));
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TEMP);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TempBlockEntity(blockPos, blockState);
    }
}
