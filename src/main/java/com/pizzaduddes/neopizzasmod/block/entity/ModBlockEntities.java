package com.pizzaduddes.neopizzasmod.block.entity;

import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.block.ModBlocks;
import com.pizzaduddes.neopizzasmod.block.entity.custom.PedestalBlockEntity;
import com.pizzaduddes.neopizzasmod.block.entity.custom.RuneStationBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NeoPizzasMod.MODID);


    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()).build(null));


    public static final Supplier<BlockEntityType<RuneStationBlockEntity>> RUNE_STATION_BE =
            BLOCK_ENTITIES.register("rune_station_be", () -> BlockEntityType.Builder.of(
                    RuneStationBlockEntity::new, ModBlocks.RUNE_STATION.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
