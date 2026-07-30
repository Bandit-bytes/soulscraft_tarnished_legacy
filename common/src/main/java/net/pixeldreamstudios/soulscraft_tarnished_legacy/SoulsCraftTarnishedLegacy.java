package net.pixeldreamstudios.soulscraft_tarnished_legacy;

import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.ItemRegistry;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.TabRegistry;

public final class SoulsCraftTarnishedLegacy {
    public static final String MOD_ID = "soulscraft_tarnished_legacy";

    private SoulsCraftTarnishedLegacy() {}

    public static void init() {
        TabRegistry.init();
        ItemRegistry.init();
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
