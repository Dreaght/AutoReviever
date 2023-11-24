package com.github.dreaght.mixins;

import com.github.dreaght.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import org.lwjgl.opengl.Display;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "runTick", at = @At("HEAD"))
    protected void runTick(CallbackInfo ci) throws AWTException {
        if (!Display.isActive()) {
            return;
        }

        if (Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            return;
        }

        if (Minecraft.getMinecraft().theWorld == null) {
            return;
        }

        ModConfig config = ModConfig.getInstance();

        if (!(config.isModEnabled())) {
            return;
        }

        Robot robot = new Robot();

        if (Minecraft.getMinecraft().thePlayer.isInvisible()) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }

        List<Entity> entities = Minecraft.getMinecraft().theWorld.getLoadedEntityList();

        for (Entity entity : entities) {
            if (entity instanceof EntityArmorStand) {
                if (entity.getDisplayName().getFormattedText().contains("REVIV")) {
                    BlockPos posPlayer = Minecraft.getMinecraft().thePlayer.getPosition();
                    BlockPos posEntity = entity.getPosition();

                    double distance = Math.sqrt(
                            Math.pow(posEntity.getX() - posPlayer.getX(), 2) +
                                    Math.pow(posEntity.getY() - posPlayer.getY(), 2) +
                                    Math.pow(posEntity.getZ() - posPlayer.getZ(), 2)
                    );

                    if (distance < 2) {
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        return;
                    }
                }
            }
        }


    }
}
