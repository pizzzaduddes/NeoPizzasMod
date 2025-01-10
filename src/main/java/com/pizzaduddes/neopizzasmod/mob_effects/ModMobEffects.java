package com.pizzaduddes.neopizzasmod.mob_effects;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.mob_effects.custom.InconceivableMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, NeoPizzasMod.MODID);


    public static final Holder<MobEffect> INCONCEIVABLE = MOB_EFFECTS.register("inconceivable",
            () -> new InconceivableMobEffect(MobEffectCategory.BENEFICIAL, 0x007F7F7F));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
