package com.mcxufly.endurance.capability;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec.DoubleValue REDUCE_RATION;
	public static ForgeConfigSpec.DoubleValue PRODUCE_RATION;

	static
	{
		ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
		CONFIG_BUILDER.comment("Endurance settings").push("endurance");
		REDUCE_RATION = CONFIG_BUILDER.comment("reduce ration").defineInRange("reduce_ration", 1D, Double.MIN_VALUE, Double.MAX_VALUE);
		PRODUCE_RATION = CONFIG_BUILDER.comment("produce rattion").defineInRange("produce_ration", 1D, Double.MIN_VALUE, Double.MAX_VALUE);

		CONFIG_BUILDER.pop();
		COMMON_CONFIG = CONFIG_BUILDER.build();
	}
}
