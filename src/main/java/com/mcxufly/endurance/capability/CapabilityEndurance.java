package com.mcxufly.endurance.capability;

import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import org.slf4j.Logger;

public class CapabilityEndurance implements ICapabilityEndurance
{
	private double endurance;
	private final double reduce_ration = Config.REDUCE_RATION.get();
	private final double produce_ration = Config.PRODUCE_RATION.get();

	public CapabilityEndurance()
	{
		this.endurance = 100;
	}

	@Override
	public double getEndurance()
	{
		return endurance;
	}

	@Override
	public void setEndurance(double endurance)
	{
		if (endurance >= 0)
		{
			this.endurance = endurance;
		}
		else
		{
			Logger logger = LogUtils.getLogger();
			logger.warn("Oops, endurance can't be less than 0.");
		}
	}

	@Override
	public void resetEndurance()
	{
		this.endurance = 100D;
	}

	@Override
	public void reduceEndurance()
	{
		if (endurance > 0) this.endurance -= this.reduce_ration;
	}

	@Override
	public void produceEndurance()
	{
		if (endurance < 100) this.endurance += this.produce_ration;
	}

	@Override
	public CompoundTag serializeNBT()
	{
		CompoundTag tag = new CompoundTag();
		tag.putDouble("endurance", this.endurance);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag compoundTag)
	{
		this.endurance = compoundTag.getInt("endurance");
	}
}
