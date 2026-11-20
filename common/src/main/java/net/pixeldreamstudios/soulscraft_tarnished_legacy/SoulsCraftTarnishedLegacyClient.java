package net.pixeldreamstudios.soulscraft_tarnished_legacy;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.ItemRegistry;

public final class SoulsCraftTarnishedLegacyClient {
    private static final ResourceLocation BLOCKING = new ResourceLocation("blocking");

    private SoulsCraftTarnishedLegacyClient() {}

    public static void registerItemProperties() {
        registerBlockingProperty(ItemRegistry.CRUCIBLE_SHIELD.get());
        registerBlockingProperty(ItemRegistry.TREE_SENTINEL_SHIELD.get());
    }

    private static void registerBlockingProperty(Item item) {
        ItemPropertiesRegistry.register(
                item,
                BLOCKING,
                (stack, level, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F
        );
    }
}
