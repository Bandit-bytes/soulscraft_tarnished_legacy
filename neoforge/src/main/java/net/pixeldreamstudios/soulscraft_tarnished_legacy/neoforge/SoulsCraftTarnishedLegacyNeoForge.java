package net.pixeldreamstudios.soulscraft_tarnished_legacy.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

import static net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy.MOD_ID;

@Mod(MOD_ID)
public final class SoulsCraftTarnishedLegacyNeoForge {
    public SoulsCraftTarnishedLegacyNeoForge(IEventBus modEventBus) {
        SoulsCraftTarnishedLegacy.init();
    }
}
