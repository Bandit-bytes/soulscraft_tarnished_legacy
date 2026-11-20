package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.CapeStyle;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.TarnishedArmorModel;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class RoninArmorRenderer extends GeoArmorRenderer<TarnishedArmorItem> {
    public RoninArmorRenderer() {
        super(new TarnishedArmorModel(
                SoulsCraftTarnishedLegacy.resource("geo/armor/ronin_armor.geo.json"),
                TarnishedArmorRendererFactory.texture("ronin"),
                CapeStyle.BLAIDD
        ));
    }
}
