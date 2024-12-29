package com.pizzaduddes.neopizzasmod.item.custom;

import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import com.pizzaduddes.neopizzasmod.block.entity.custom.TempBlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TempStickItem extends Item {
    public TempStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(context.getClickedPos());
            if (level.getBlockState(context.getClickedPos()).is(ModBlocks.TEMP_BLOCK)) {
                if (entity instanceof TempBlockEntity tempEntity) {
                    tempEntity.setValue(500);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
