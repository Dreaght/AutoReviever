package com.github.dreaght

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.KeyBind
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import cc.polyfrost.oneconfig.config.data.OptionSize
import cc.polyfrost.oneconfig.gui.OneConfigGui
import cc.polyfrost.oneconfig.gui.animations.EaseInOutQuad
import cc.polyfrost.oneconfig.utils.Notifications
import cc.polyfrost.oneconfig.utils.gui.GuiUtils
import net.weavemc.loader.api.event.EventBus
import org.lwjgl.input.Keyboard

object ModConfig : Config(Mod("AutoReviver", ModType.HYPIXEL), "autoreviver.json", false) {
    @KeyBind(
        name = "Toggle AutoReviver",
        size = OptionSize.DUAL,
        subcategory = "Keybinds",
    )
    var toggleKeyBind: OneKeyBind = OneKeyBind(Keyboard.KEY_NONE)


    init {
        registerToggleKeyBind(toggleKeyBind)
        initialize()
    }

    fun Config.registerToggleKeyBind(oneKeyBind: OneKeyBind) =
        registerKeyBind(oneKeyBind) {
            enabled = !enabled
            sendModuleToggleStateNotification()
            save()
            refreshOneConfigGui()
        }


    fun Config.sendModuleToggleStateNotification() =
        sendNotification(
            mod.name,
            if (enabled) "(+) Enabled" else "(-) Disabled",
            2500f
        )


    fun refreshOneConfigGui() {
        OneConfigGui.INSTANCE?.let(EventBus::unsubscribe)
        OneConfigGui.INSTANCE = null
    }

    fun sendNotification(title: String, message: String, duration: Float = 4000f, progressBar: Boolean = true) {
        val easeInOutQuad = EaseInOutQuad(duration.toInt(), 0f, 1f, false)
        if (progressBar) {
            Notifications.INSTANCE.send(title, message, -1f, { easeInOutQuad.get(GuiUtils.getDeltaTime()) }, null)
        } else {
            Notifications.INSTANCE.send(title, message, duration, null, null)
        }
    }

}
