package net.pixeldreamstudios.soulscraft_tarnished_legacy.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.forge.loot.TarnishedLootModifiers;

import static net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy.MOD_ID;

@Mod(MOD_ID)
public final class SoulsCraftTarnishedLegacyForge {
    public SoulsCraftTarnishedLegacyForge(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        EventBuses.registerModEventBus(MOD_ID, modEventBus);
        SoulsCraftTarnishedLegacy.init();
        TarnishedLootModifiers.register(modEventBus);
    }
}
