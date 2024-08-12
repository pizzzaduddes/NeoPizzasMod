package com.pizzaduddes.neopizzasmod.datagen;

import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.RAW_TANZANITE_BLOCK.get());
        dropSelf(ModBlocks.TANZANITE_BLOCK.get());
        this.add(ModBlocks.TANZANITE_ORE.get(),
                block -> createOreDrop(ModBlocks.TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()));
        this.add(ModBlocks.DEEPSLATE_TANZANITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_TANZANITE_ORE.get(), ModItems.RAW_TANZANITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
