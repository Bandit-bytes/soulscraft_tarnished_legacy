package net.pixeldreamstudios.soulscraft_tarnished_legacy.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacyClient;

@Mod.EventBusSubscriber(
        modid = SoulsCraftTarnishedLegacy.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class SoulsCraftTarnishedLegacyForgeClient {
    private SoulsCraftTarnishedLegacyForgeClient() {}

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(
                SoulsCraftTarnishedLegacyClient::registerItemProperties
        );
    }
}
