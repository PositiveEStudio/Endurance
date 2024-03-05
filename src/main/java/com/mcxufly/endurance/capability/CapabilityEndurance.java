package com.mcxufly.endurance.capability;

import net.minecraft.nbt.CompoundTag;

public class CapabilityEndurance implements ICapabilityEndurance
{
	private int endurance;

	public CapabilityEndurance(int endurance)
	{
		this.endurance = endurance;
	}

	@Override
	public int getEndurance()
	{
		return endurance;
	}

	@Override
	public void setEndurance(int endurance)
	{
		this.endurance = endurance;
	}

	@Override
	public CompoundTag serializeNBT()
	{
		CompoundTag tag = new CompoundTag();
		tag.putInt("endurance", this.endurance);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag compoundTag)
	{
		this.endurance = compoundTag.getInt("endurance");
	}
}
