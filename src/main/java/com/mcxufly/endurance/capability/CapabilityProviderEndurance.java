package com.mcxufly.endurance.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityProviderEndurance implements ICapabilityProvider, INBTSerializable<CompoundTag>
{
	private ICapabilityEndurance capabilityEndurance;

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction)
	{
		return capability == EnduranceCapability.ENDURANCE_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
	}

	@NotNull
	ICapabilityEndurance getOrCreateCapability()
	{
		if (capabilityEndurance == null)
		{
			this.capabilityEndurance = new CapabilityEndurance();
		}
		return capabilityEndurance;
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap)
	{
		return ICapabilityProvider.super.getCapability(cap);
	}

	@Override
	public CompoundTag serializeNBT()
	{
		return getOrCreateCapability().serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag compoundTag)
	{
		getOrCreateCapability().deserializeNBT(compoundTag);
	}
}
