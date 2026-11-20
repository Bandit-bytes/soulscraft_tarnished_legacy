package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
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

    public static ResourceLocation texture(String setId) {
        String path = switch (setId) {
            case "night_rider" -> "night_rider";
            case "blaidd" -> "blaidd_armor";
            case "ronin" -> "ronin_armor";
            case "crucible_knight" -> "crucible_knight_armor";
            case "crucible_tree" -> "crucible_tree_armor";
            case "tree_sentinel" -> "tree_sentinel";
            case "maliketh" -> "maliketh_armor";
            case "radahn" -> "radahn_armor";
            default -> throw new IllegalArgumentException("Unknown Tarnished Legacy armor set: " + setId);
        };
        return SoulsCraftTarnishedLegacy.resource("textures/armor/" + path + ".png");
    }
}
