package com.mcxufly.endurance.event;

import com.mcxufly.endurance.Endurance;
import com.mcxufly.endurance.capability.CapabilityProviderEndurance;
import com.mcxufly.endurance.capability.EnduranceCapability;
import com.mcxufly.endurance.capability.ICapabilityEndurance;
import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEventHandler
{
	@SubscribeEvent
	public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event)
	{
		Entity entity = event.getObject();
		if (entity instanceof Player)
		{
			event.addCapability(new ResourceLocation(Endurance.MOD_ID, "endurance"), new CapabilityProviderEndurance());
		}
	}

	@SubscribeEvent
	public static void onPlayerCloned(PlayerEvent.Clone event)
	{
		if (!event.isWasDeath())
		{
			LazyOptional<ICapabilityEndurance> oldEndurance = event.getOriginal().getCapability(EnduranceCapability.ENDURANCE_CAPABILITY);
			LazyOptional<ICapabilityEndurance> newEndurance = event.getPlayer().getCapability(EnduranceCapability.ENDURANCE_CAPABILITY);
			if (oldEndurance.isPresent() && newEndurance.isPresent())
			{
				newEndurance.ifPresent((newCapability) ->
				{
					oldEndurance.ifPresent((oldCapability ->
					{
						newCapability.deserializeNBT(oldCapability.serializeNBT());
					}));
				});
			}
		}
	}
}
