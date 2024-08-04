package com.pizzaduddes.neopizzasmod.item;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoPizzasMod.MODID);

    public static final DeferredItem<Item> TANZANITE = ITEMS.registerItem("tanzanite",
            Item::new, new Item.Properties()
                    .stacksTo(80)
                    .rarity(Rarity.EPIC));

    public static void register(IEventBus eventbus) {
    }
}
