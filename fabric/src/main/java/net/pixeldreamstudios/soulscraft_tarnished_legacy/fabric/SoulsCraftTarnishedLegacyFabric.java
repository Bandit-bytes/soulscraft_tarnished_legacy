package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.fabricmc.api.ModInitializer;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric.loot.TarnishedLootTableModifiers;

public final class SoulsCraftTarnishedLegacyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SoulsCraftTarnishedLegacy.init();
        TarnishedLootTableModifiers.init();
    }
}
