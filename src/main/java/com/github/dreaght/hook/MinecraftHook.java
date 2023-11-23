package com.github.dreaght.hook;

import net.weavemc.loader.api.Hook;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class MinecraftHook extends Hook {
    public MinecraftHook() {
        super("net/minecraft/client/Minecraft");
    }

    @Override
    public void transform(@NotNull ClassNode classNode, @NotNull AssemblerConfig assemblerConfig) {
        classNode.methods.stream()
                .filter(m -> m.name.equals("update"))
                .findFirst().orElseThrow()
                .instructions.insert(
                        new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                Type.getInternalName(MinecraftHook.class),
                                "onUpdate",
                                "()V"
                        )
                );
    }

    public static void onUpdate(int x, int y) {
        // Ваш код хука
        System.out.printf("Hook called with position (%d, %d)\n", x, y);
    }
}
