package com.pizzaduddes.neopizzasmod.datagen;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {




    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NeoPizzasMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TANZANITE_BLOCK);
        blockWithItem(ModBlocks.RAW_TANZANITE_BLOCK);
        blockWithItem(ModBlocks.TANZANITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TANZANITE_ORE);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
