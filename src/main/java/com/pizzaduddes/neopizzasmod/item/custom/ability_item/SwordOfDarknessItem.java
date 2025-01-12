package com.pizzaduddes.neopizzasmod.item.custom.ability_item;

import com.pizzaduddes.neopizzasmod.item.ModItems;
import com.pizzaduddes.neopizzasmod.mob_effects.ModMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SwordOfDarknessItem extends Item implements AbilityItem {
    public SwordOfDarknessItem(Properties properties) {
        super(properties);
    }
    private static boolean isCooldownActiveSOD = false;
    private static int currentAbility = 0;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide()) {
            player.addEffect(new MobEffectInstance(ModMobEffects.INCONCEIVABLE, 400, 0, false, false));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1.0f, 0.02f);
            if (!player.getCooldowns().isOnCooldown(this)) {
                player.getCooldowns().addCooldown(this, 800);
                isCooldownActiveSOD = true;
            }
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) {
            if (!player.getCooldowns().isOnCooldown(this) && isCooldownActiveSOD == true) {
                isCooldownActiveSOD = false;
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1.0f, 3f);
            }
        }
    }

    public static boolean getCooldown() {
        return isCooldownActiveSOD;
    }

    public static float getRemainingCooldown(Player player) {
        float remainingCooldown = player.getCooldowns().getCooldownPercent(ModItems.SWORD_OF_DARKNESS.get(), 0.0f );
        return remainingCooldown;
    }


    @Override
    public int getAbilityValue() {
        return currentAbility;
    }

    @Override
    public void setAbilityValue(int newAbility) {
        currentAbility = newAbility;
    }

    @Override
    public int abilityAmount() {
        return 5;
    }
}
