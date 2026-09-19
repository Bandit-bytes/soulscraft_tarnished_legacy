package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class TarnishedArmorRendererFactory {
    private TarnishedArmorRendererFactory() {}

    public static GeoArmorRenderer<?> create(String setId) {
        return switch (setId) {
            case "night_rider" -> new NightRiderArmorRenderer();
            case "blaidd" -> new BlaiddArmorRenderer();
            case "ronin" -> new RoninArmorRenderer();
            case "crucible_knight" -> new CrucibleKnightArmorRenderer();
            case "crucible_tree" -> new CrucibleKnightVariantArmorRenderer();
            case "tree_sentinel" -> new TreeSentinelArmorRenderer();
            case "maliketh" -> new MalikethArmorRenderer();
            case "radahn" -> new RadahnArmorRenderer();
            default -> throw new IllegalArgumentException("Unknown Tarnished Legacy armor set: " + setId);
        };
    }
}
