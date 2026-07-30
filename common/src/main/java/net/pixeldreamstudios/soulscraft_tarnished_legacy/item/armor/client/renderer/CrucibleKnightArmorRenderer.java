package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.CapeStyle;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.TarnishedArmorModel;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class CrucibleKnightArmorRenderer extends GeoArmorRenderer<TarnishedArmorItem> {
    public CrucibleKnightArmorRenderer() {
        super(new TarnishedArmorModel(
                SoulsCraftTarnishedLegacy.resource("geo/armor/crucible_knight_armor.geo.json"),
                SoulsCraftTarnishedLegacy.resource("textures/armor/crucible_knight_armor.png"),
                CapeStyle.NONE
        ));
    }
}
