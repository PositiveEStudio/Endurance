package com.mcxufly.endurance.event;

import com.mcxufly.endurance.capability.EnduranceCapability;
import com.mcxufly.endurance.capability.ICapabilityEndurance;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickEvent
{
	@SubscribeEvent
	public static void TickEnduranceEvent(net.minecraftforge.event.TickEvent.PlayerTickEvent tickEvent)
	{
		if (tickEvent.side.isServer() && (tickEvent.player instanceof ServerPlayer serverPlayer) && !serverPlayer.isCreative())
		{
			LazyOptional<ICapabilityEndurance> enduranceCapability = tickEvent.player.getCapability(EnduranceCapability.ENDURANCE_CAPABILITY);
			if (enduranceCapability.isPresent())
			{
				if (serverPlayer.isSprinting())
				{
					enduranceCapability.ifPresent(ICapabilityEndurance::reduceEndurance);
				}
				else if (serverPlayer.isShiftKeyDown())
				{
					enduranceCapability.ifPresent(ICapabilityEndurance::produceEndurance);
				}

				{
					enduranceCapability.ifPresent((cap) ->
					{
						double endurance = cap.getEndurance();
						if (endurance == 100D) serverPlayer.removeEffect(MobEffects.CONFUSION);
						else
						{
							if (endurance <= 60D)
							{
								serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 2));
							}
							if (endurance <= 20D)
							{
								if (!serverPlayer.hasEffect(MobEffects.CONFUSION))
									serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 3));
							}
							if (endurance == 0)
							{
								serverPlayer.moveTo(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
							}
						}
					});

				}
			}
		}
	}
}
