package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.CapeStyle;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model.TarnishedArmorModel;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class NightRiderArmorRenderer extends GeoArmorRenderer<TarnishedArmorItem> {
    public NightRiderArmorRenderer() {
        super(new TarnishedArmorModel(
                SoulsCraftTarnishedLegacy.resource("geo/armor/night_rider.geo.json"),
                SoulsCraftTarnishedLegacy.resource("textures/armor/night_rider.png"),
                CapeStyle.MALIKETH
        ));
    }
}
