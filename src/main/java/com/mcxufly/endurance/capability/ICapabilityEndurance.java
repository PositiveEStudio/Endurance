package com.mcxufly.endurance.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICapabilityEndurance extends INBTSerializable<CompoundTag>
{
    double getEndurance();
    void setEndurance(double endurance);
    void resetEndurance();
    void reduceEndurance();
    void produceEndurance();
}
