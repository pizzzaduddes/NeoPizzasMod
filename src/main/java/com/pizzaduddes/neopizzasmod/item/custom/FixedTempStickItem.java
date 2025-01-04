package com.pizzaduddes.neopizzasmod.item.custom;

import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import com.pizzaduddes.neopizzasmod.block.entity.custom.TempBlockEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FixedTempStickItem extends Item {
    public FixedTempStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(context.getClickedPos());
            if (level.getBlockState(context.getClickedPos()).is(ModBlocks.TEMP_BLOCK)) {
                if (entity instanceof TempBlockEntity tempEntity) {
                    if (player instanceof ServerPlayer serverPlayer){
                        tempEntity.negateFixedTemp();
                        serverPlayer.sendSystemMessage(Component.literal("FixedTemperature: " + tempEntity.getFixedTemp()));
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }
}
