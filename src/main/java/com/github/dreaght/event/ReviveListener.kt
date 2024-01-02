package com.github.dreaght.event

import com.github.dreaght.ModConfig
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityArmorStand
import net.minecraft.util.BlockPos
import net.weavemc.loader.api.event.SubscribeEvent
import net.weavemc.loader.api.event.TickEvent
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.pow

object ReviveListener {
    val minecraft = Minecraft.getMinecraft()
    var isPressed = false
    var keepSneaking = false

    @SubscribeEvent
    fun onTick(event: TickEvent.Post) {
        if (with(ModConfig) { !enabled }) {
            return
        }

        if (minecraft?.thePlayer == null) {
            return
        }

        if (isPressed && (minecraft.currentScreen != null
            || minecraft.thePlayer.isInvisible)) {
            if (isPressed) {
                turnSneak(false)
            }

        }

        keepSneaking = false

        val entities: List<Entity> = Minecraft.getMinecraft().theWorld.getLoadedEntityList()
        for (entity in entities) {
            if (entity is EntityArmorStand) {
                if (entity.displayName.formattedText.contains("REVIV")) {
                    val posPlayer: BlockPos = Minecraft.getMinecraft().thePlayer.position
                    val posEntity: BlockPos = entity.position

                    val distance: Double = kotlin.math.sqrt(
                        (posEntity.x - posPlayer.x).toDouble().pow(2.0) +
                                (posEntity.y - posPlayer.y).toDouble().pow(2.0) +
                                (posEntity.z - posPlayer.z).toDouble().pow(2.0)
                    )

                    if (distance < 2) {
                        keepSneaking = true

                        if (!isPressed && minecraft.currentScreen == null && !minecraft.thePlayer.isInvisible) {
                            turnSneak(true)
                        }

                        return
                    }
                }
            }
        }

        if (!keepSneaking && isPressed) {
            turnSneak(false)
        }
    }

    fun turnSneak(boolean: Boolean) {
        val randomDelay = ThreadLocalRandom.current().nextInt(20, 40)

        Thread {
            Thread.sleep(randomDelay.toLong())
            KeyBinding.setKeyBindState(minecraft.gameSettings.keyBindSneak.keyCode, boolean)
            isPressed = boolean
        }.start()
    }
}