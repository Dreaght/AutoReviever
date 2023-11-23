package com.github.dreaght.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "update", at = @At("HEAD"))
    protected void onUpdate(CallbackInfo ci) {
        System.out.printf("UPDATE");
    }
}
