package com.mcxufly.endurance.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICapabilityEndurance extends INBTSerializable<CompoundTag>
{
    int getEndurance();
    void setEndurance(int endurance);
}
