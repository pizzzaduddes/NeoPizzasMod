package com.pizzaduddes.neopizzasmod.item;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeoPizzasMod.MODID);

    public static final Supplier<CreativeModeTab> NEO_PIZZAS_TAB =
            CREATIVE_MODE_TABS.register("neo_pizzas_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("tab.neopizzasmod"))
                            .icon(() -> new ItemStack(ModItems.TANZANITE.get()))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.TANZANITE.get());
                                output.accept(ModItems.RAW_TANZANITE.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
