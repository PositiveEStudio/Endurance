package com.mcxufly.endurance;

import com.mcxufly.endurance.capability.ICapabilityEndurance;
import com.mcxufly.endurance.gui.GUIEndurance;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("endurance")
public class Endurance
{
	public static final String MOD_ID = "endurance";
	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

	private static final Logger LOGGER = LogUtils.getLogger();

	public Endurance()
	{
		bus.addListener(this::setup);
		bus.addListener(this::enqueueIMC);
		bus.addListener(this::processIMC);
		bus.addListener(this::registerCapabilities);
		bus.addListener(this::onClientSetup);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		LOGGER.info("Endurance is setting up.");
	}

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
	}

	private void processIMC(final InterModProcessEvent event)
	{
	}

	private void onClientSetup(final FMLClientSetupEvent event)
	{
		GUIEndurance.registerGUIEndurance();
	}

	public void registerCapabilities(RegisterCapabilitiesEvent event)
	{
		event.register(ICapabilityEndurance.class);
	}
}
