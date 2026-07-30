package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.CapeStyle;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.TarnishedArmorModel;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class TreeSentinelArmorRenderer extends GeoArmorRenderer<TarnishedArmorItem> {
    public TreeSentinelArmorRenderer() {
        super(new TarnishedArmorModel(
                SoulsCraftTarnishedLegacy.resource("geo/armor/tree_sentinel_armor.geo.json"),
                SoulsCraftTarnishedLegacy.resource("textures/armor/tree_sentinel.png"),
                CapeStyle.TREE_SENTINEL
        ));
    }
}
