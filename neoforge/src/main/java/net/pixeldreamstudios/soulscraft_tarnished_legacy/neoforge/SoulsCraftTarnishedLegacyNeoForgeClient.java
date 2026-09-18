package net.pixeldreamstudios.soulscraft_tarnished_legacy.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacyClient;

@EventBusSubscriber(
        modid = SoulsCraftTarnishedLegacy.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class SoulsCraftTarnishedLegacyNeoForgeClient {

    private SoulsCraftTarnishedLegacyNeoForgeClient() {}

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(
                SoulsCraftTarnishedLegacyClient::registerItemProperties
        );
    }
}