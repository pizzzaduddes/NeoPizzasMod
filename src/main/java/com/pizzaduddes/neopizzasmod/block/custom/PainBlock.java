package com.pizzaduddes.neopizzasmod.block.custom;

import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class PainBlock extends Block {
    public PainBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        boolean isInWater = entity.isInWater();

            if (!level.isClientSide && entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.isInWater()) {
                    livingEntity.setInvisible(true);
                } else {
                    livingEntity.hurt(entity.damageSources().generic(), 2f);
                    if (livingEntity.isShiftKeyDown()) {
                        level.setBlock(pos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
                    }
                }
            }

        super.stepOn(level, pos, state, entity);
    }
}
