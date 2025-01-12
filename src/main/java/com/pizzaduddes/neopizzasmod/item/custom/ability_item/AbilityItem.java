package com.pizzaduddes.neopizzasmod.item.custom.ability_item;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public interface AbilityItem {
    int getAbilityValue();
    void setAbilityValue(int value);
    default void onScroll(Player player, double scrollDelta) {
        int currentAbility = getAbilityValue();
        if (scrollDelta > 0.1) {
            currentAbility++;
            setAbilityValue(currentAbility);
        } else if (scrollDelta < -0.1) {
            currentAbility--;
            setAbilityValue(currentAbility);
        }
        setAbilityValue(currentAbility);
        int abilityAmount = abilityAmount();
        currentAbility = Mth.clamp(currentAbility, 0, abilityAmount);
        player.sendSystemMessage(Component.literal("Current Ability: " + currentAbility));
    }
    int abilityAmount();
}
