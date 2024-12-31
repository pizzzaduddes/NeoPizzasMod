package com.pizzaduddes.neopizzasmod.block.entity.custom;

import com.pizzaduddes.neopizzasmod.block.custom.TempBlock;
import com.pizzaduddes.neopizzasmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TempBlockEntity extends BlockEntity {
    private double value = 0;
    private boolean fixedTemp = false;

    public TempBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TEMP_BE.get(), pos, blockState);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double newValue) {
        this.value = newValue;
        updateBlockState();
    }

    public boolean getFixedTemp() {
        return fixedTemp;
    }

    public void negateFixedTemp() {
        this.fixedTemp = !fixedTemp;
    }

    public void tick1(Level level, BlockPos pos, BlockState state, TempBlockEntity blockEntity) {

        performHeatConduction(level, pos);

    }

    private void performHeatConduction(Level level, BlockPos pos){
        double alpha = 1.0e-1;
        double deltaT = 1.0;
        double deltaX = 1.0;

        double currentTemp = getValue();

        double neighbourTempSum = 0;
        int neighbourCount = 0;

        for (Direction direction : Direction.values()) {
            BlockPos neighbourPos = pos.relative(direction);
            BlockEntity neighbourEntity  = level.getBlockEntity(neighbourPos);

            if (neighbourEntity instanceof TempBlockEntity neighbourBlockEntity) {
                neighbourTempSum += neighbourBlockEntity.getValue();
                neighbourCount++;
            }
        }
        //boolean isTempFixed = getFixedTemp();

        if (neighbourCount == 0 ) return;
        if (fixedTemp == true) return;

        double averageNeighbourTemp = neighbourTempSum / neighbourCount;

        double newTemp = currentTemp + alpha * deltaT / (deltaX * deltaX) * (averageNeighbourTemp - currentTemp);
        setValue(newTemp);

    }

    private void updateBlockState() {
        if (level != null && !level.isClientSide()) {
            int temprange = (int) Math.min(value / 100, 4);
            BlockState state = getBlockState().setValue(TempBlock.TEMP, temprange);
            level.setBlock(worldPosition, state, 3);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.value = tag.getDouble("Value");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putDouble("Value", this.value);
    }
}
