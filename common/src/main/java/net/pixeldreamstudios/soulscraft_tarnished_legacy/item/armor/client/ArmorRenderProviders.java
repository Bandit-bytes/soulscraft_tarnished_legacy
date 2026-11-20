package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import java.util.function.Supplier;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

/**
 * GeckoLib for 1.20.1 hooks armor renderers differently per loader: Fabric uses its own
 * {@code RenderProvider}, Forge uses {@code IClientItemExtensions}. Each loader supplies the matching object.
 */
public final class ArmorRenderProviders {
    private ArmorRenderProviders() {}

    @ExpectPlatform
    public static Object create(Supplier<GeoArmorRenderer<?>> rendererFactory) {
        throw new AssertionError();
    }
}
