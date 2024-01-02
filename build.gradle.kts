plugins {
    kotlin("jvm") version ("1.8.0")
    id("com.github.weave-mc.weave-gradle") version "649dba7468"
}

group = "com.github.dreaght"
version = "1.1"

minecraft.version("1.8.9")

repositories {
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")

    maven("https://repo.polyfrost.cc/releases")
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:v0.2.4")

    implementation("cc.polyfrost:oneconfig-1.8.9-forge:0.2.0-alpha+")

    compileOnly("org.spongepowered:mixin:0.8.5")

}

kotlin {
    jvmToolchain(17)
}

tasks.jar {
    archiveFileName.set("AutoReviver.jar")
}
