package com.pizzaduddes.neopizzasmod.item;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.item.custom.AntiSmelterItem;
import com.pizzaduddes.neopizzasmod.item.custom.FixedTempStickItem;
import com.pizzaduddes.neopizzasmod.item.custom.SwordOfDarknessItem;
import com.pizzaduddes.neopizzasmod.item.custom.TempStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoPizzasMod.MODID);


    public static final DeferredItem<Item> RUNE_ROCK = ITEMS.register("rune_rock",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TANZANITE = ITEMS.register("tanzanite",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> RAW_TANZANITE = ITEMS.register("raw_tanzanite",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> ANTI_SMELTER = ITEMS.register("anti_smelter",
            () -> new AntiSmelterItem(new Item.Properties().durability(256)));

    public static final DeferredItem<Item> TEMP_STICK = ITEMS.register("temp_stick",
            () -> new TempStickItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> FIXED_TEMP_STICK = ITEMS.register("fixed_temp_stick",
            () -> new FixedTempStickItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> SWORD_OF_DARKNESS = ITEMS.register("sword_of_darkness",
            () -> new SwordOfDarknessItem(new Item.Properties().rarity(Rarity.EPIC)));

    // Energy Rune

    public static final DeferredItem<Item> RUNE_ENERGY_UNFILLED = ITEMS.register("rune_energy_unfilled",
            () -> new Item(new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
