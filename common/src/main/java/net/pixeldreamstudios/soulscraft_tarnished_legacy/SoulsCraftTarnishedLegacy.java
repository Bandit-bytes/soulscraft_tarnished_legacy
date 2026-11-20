package net.pixeldreamstudios.soulscraft_tarnished_legacy;

import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.event.TarnishedEvents;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.EffectRegistry;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.ItemRegistry;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.TabRegistry;

public final class SoulsCraftTarnishedLegacy {
    public static final String MOD_ID = "soulscraft_tarnished_legacy";

    private SoulsCraftTarnishedLegacy() {}

    public static void init() {
        EffectRegistry.init();
        TabRegistry.init();
        ItemRegistry.init();
        TarnishedEvents.init();
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
