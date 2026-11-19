package net.pixeldreamstudios.soulscraft_tarnished_legacy.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(SoulsCraftTarnishedLegacy.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> TARNISHED_LEGACY_TAB = TABS.register(
            "tarnished_legacy",
            () -> CreativeTabRegistry.create(
                    Component.translatable("category.soulscraft_tarnished_legacy"),
                    () -> ItemRegistry.MALIKETH_HELMET.get().getDefaultInstance()
            )
    );

    private TabRegistry() {}

    public static void init() {
        TABS.register();
    }
}
