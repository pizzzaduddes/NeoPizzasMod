package com.pizzaduddes.neopizzasmod.datagen;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NeoPizzasMod.MODID, existingFileHelper);
    }




    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TANZANITE_BLOCK.get())
                .add(ModBlocks.RAW_TANZANITE_BLOCK.get())
                .add(ModBlocks.TANZANITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_TANZANITE_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TANZANITE_BLOCK.get())
                .add(ModBlocks.TANZANITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_TANZANITE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_TANZANITE_BLOCK.get());
    }
}
