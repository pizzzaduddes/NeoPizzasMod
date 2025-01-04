package com.pizzaduddes.neopizzasmod.datagen;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NeoPizzasMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TANZANITE.get());
        basicItem(ModItems.RAW_TANZANITE.get());
        basicItem(ModItems.ANTI_SMELTER.get());
        basicItem(ModItems.RUNE_ROCK.get());
        basicItem(ModItems.TEMP_STICK.get());
        basicItem(ModItems.FIXED_TEMP_STICK.get());

        //Energy Rune

        basicItem(ModItems.RUNE_ENERGY_UNFILLED.get());
    }
}
