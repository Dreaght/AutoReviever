package com.github.dreaght;

import com.github.dreaght.listener.RenderGameOverlayListener;
import net.minecraft.client.Minecraft;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.event.EventBus;
import net.weavemc.loader.api.event.KeyboardEvent;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

public class AutoReviver implements ModInitializer {
    @Override
    public void preInit() {
        System.out.println("Initializing ExampleMod!");

        ModConfig config = ModConfig.getInstance();

        EventBus.subscribe(KeyboardEvent.class, e -> {
            if (Minecraft.getMinecraft().currentScreen == null && e.getKeyState()
                    && Objects.equals(Keyboard.getKeyName(e.getKeyCode()), "O")) {
                config.setModEnabled(!config.isModEnabled());
            }
        });

        EventBus.subscribe(new RenderGameOverlayListener());
    }
}