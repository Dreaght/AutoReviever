package com.github.dreaght.listener;

import com.github.dreaght.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.weavemc.loader.api.event.RenderGameOverlayEvent;
import net.weavemc.loader.api.event.SubscribeEvent;

public class RenderGameOverlayListener {
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        ModConfig config = ModConfig.getInstance();

        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawStringWithShadow(String.format("(Press 'O' to toggle) AutoReviver: %s", config.isModEnabled()), 10, 30, 0xFFFFFF);
    }
}
