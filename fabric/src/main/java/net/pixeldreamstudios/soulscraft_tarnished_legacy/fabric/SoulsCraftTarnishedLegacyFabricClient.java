package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacyClient;

public final class SoulsCraftTarnishedLegacyFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SoulsCraftTarnishedLegacyClient.registerItemProperties();
    }
}