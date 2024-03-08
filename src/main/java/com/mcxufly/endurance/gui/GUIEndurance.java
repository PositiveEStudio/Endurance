package com.mcxufly.endurance.gui;

import com.mcxufly.endurance.Endurance;
import com.mcxufly.endurance.capability.EnduranceCapability;
import com.mcxufly.endurance.capability.ICapabilityEndurance;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;

@OnlyIn(Dist.CLIENT)
public class GUIEndurance
{
	public static ICapabilityEndurance ENDURANCE = null;
	public static final ResourceLocation ENDURANCE_ICONS = new ResourceLocation(Endurance.MOD_ID, "textures/gui/endurance_bar.png");
	public static boolean show = true;
	static Minecraft minecraft = Minecraft.getInstance();

	public static final IIngameOverlay ENDURANCE_OVERLAY = OverlayRegistry.registerOverlayAbove(ForgeIngameGui.JUMP_BAR_ELEMENT, "Endurance BAr", ((gui, poseStack, partialTick, width, height) ->
	{
		boolean isMounted = minecraft.player.isPassenger();
		show = true;
		if (!isMounted && !minecraft.options.hideGui && !gui.shouldDrawSurvivalElements())
		{
			gui.setupOverlayRenderState(true, false);
			render(gui, width, height, poseStack);
		}
	}));

	public static void registerGUIEndurance()
	{
	}

	public static void render(ForgeIngameGui gui, int width, int height, PoseStack poseStack)
	{
		minecraft.getProfiler().push("endurance");
		if (ENDURANCE == null)
			ENDURANCE = minecraft.player.getCapability(EnduranceCapability.ENDURANCE_CAPABILITY).orElse(null);

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, ENDURANCE_ICONS);
		int left = width / 2 + 91;
		int top = height - 39;

		double endurance = ENDURANCE.getEndurance();
		int draw_length = (int) (endurance * 182D / 100D);

		GuiComponent.blit(poseStack, left, top, 0, 0, draw_length, 5, 182, 5);
		RenderSystem.disableBlend();
		RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);

		minecraft.getProfiler().pop();
	}
}
