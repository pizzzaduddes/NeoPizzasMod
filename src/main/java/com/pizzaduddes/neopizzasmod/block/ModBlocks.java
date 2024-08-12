package com.pizzaduddes.neopizzasmod.block;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.block.custom.PainBlock;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(NeoPizzasMod.MODID);


    public static final DeferredBlock<Block> TANZANITE_BLOCK = registerBlock("tanzanite_block",
            () -> new PainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RAW_TANZANITE_BLOCK = registerBlock("raw_tanzanite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_GOLD_BLOCK)
                    .sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TANZANITE_ORE = registerBlock("tanzanite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DEEPSLATE_TANZANITE_ORE = registerBlock("deepslate_tanzanite_ore",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

