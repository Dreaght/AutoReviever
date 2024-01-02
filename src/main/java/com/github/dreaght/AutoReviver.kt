package com.github.dreaght

import com.github.dreaght.event.ReviveListener
import net.weavemc.loader.api.ModInitializer
import net.weavemc.loader.api.event.EventBus
import net.weavemc.loader.api.event.StartGameEvent

class AutoReviver : ModInitializer {
    override fun preInit() {
        println("Initializing AutoReviver!")
        EventBus.subscribe(StartGameEvent.Post::class.java) { ModConfig }
        EventBus.subscribe(StartGameEvent.Post::class.java) { EventBus.subscribe(ReviveListener) }
    }
}