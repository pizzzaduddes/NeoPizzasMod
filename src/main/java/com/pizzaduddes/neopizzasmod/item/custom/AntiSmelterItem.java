package com.pizzaduddes.neopizzasmod.item.custom;

import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class AntiSmelterItem extends Item {
    public AntiSmelterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if(!level.isClientSide()) {
            if(level.getBlockState(context.getClickedPos()).is(ModBlocks.TANZANITE_BLOCK)){
                level.destroyBlock(context.getClickedPos(), false, context.getPlayer());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.setBlock(context.getClickedPos(), ModBlocks.RAW_TANZANITE_BLOCK.get().defaultBlockState(), 3);

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
