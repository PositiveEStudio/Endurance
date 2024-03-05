package com.mcxufly.endurance.event;

import com.mcxufly.endurance.capability.CapabilityEndurance;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler
{
	@SubscribeEvent
	public static void onSetupEvent(RegisterCapabilitiesEvent event)
	{
		event.register(CapabilityEndurance.class);
	}
}
