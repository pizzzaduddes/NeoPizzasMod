package com.pizzaduddes.neopizzasmod.block;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoPizzasMod.MODID);

    public static final DeferredBlock<Block> TANZANITE_BLOCK = BLOCKS.register("tanzanite_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .sound(SoundType.AMETHYST)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
