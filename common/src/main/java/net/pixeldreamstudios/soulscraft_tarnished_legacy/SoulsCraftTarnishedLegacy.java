package net.pixeldreamstudios.soulscraft_tarnished_legacy;

import mod.azure.azurelib.common.animation.cache.AzIdentityRegistry;
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

    public static void initClient() {
        TarnishedLegacyClient.init();
    }

    public static void initAzIdentityRegistry() {
        AzIdentityRegistry.register(
                ItemRegistry.NIGHT_RIDER_HELMET.get(),
                ItemRegistry.NIGHT_RIDER_CHESTPLATE.get(),
                ItemRegistry.NIGHT_RIDER_LEGGINGS.get(),
                ItemRegistry.NIGHT_RIDER_BOOTS.get(),

                ItemRegistry.BLAIDD_HELMET.get(),
                ItemRegistry.BLAIDD_CHESTPLATE.get(),
                ItemRegistry.BLAIDD_LEGGINGS.get(),
                ItemRegistry.BLAIDD_BOOTS.get(),

                ItemRegistry.RONIN_HELMET.get(),
                ItemRegistry.RONIN_CHESTPLATE.get(),
                ItemRegistry.RONIN_LEGGINGS.get(),
                ItemRegistry.RONIN_BOOTS.get(),

                ItemRegistry.CRUCIBLE_KNIGHT_HELMET.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_CHESTPLATE.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_LEGGINGS.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_BOOTS.get(),

                ItemRegistry.CRUCIBLE_TREE_HELMET.get(),
                ItemRegistry.CRUCIBLE_TREE_CHESTPLATE.get(),
                ItemRegistry.CRUCIBLE_TREE_LEGGINGS.get(),
                ItemRegistry.CRUCIBLE_TREE_BOOTS.get(),

                ItemRegistry.TREE_SENTINEL_HELMET.get(),
                ItemRegistry.TREE_SENTINEL_CHESTPLATE.get(),
                ItemRegistry.TREE_SENTINEL_LEGGINGS.get(),
                ItemRegistry.TREE_SENTINEL_BOOTS.get(),

                ItemRegistry.MALIKETH_HELMET.get(),
                ItemRegistry.MALIKETH_CHESTPLATE.get(),
                ItemRegistry.MALIKETH_LEGGINGS.get(),
                ItemRegistry.MALIKETH_BOOTS.get()
        );
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
