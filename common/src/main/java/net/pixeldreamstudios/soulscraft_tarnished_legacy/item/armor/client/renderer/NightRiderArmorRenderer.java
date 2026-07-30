package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public class NightRiderArmorRenderer extends AzArmorRenderer {
    public NightRiderArmorRenderer() { super(AzArmorRendererConfig.builder(SoulsCraftTarnishedLegacy.resource("geo/armor/night_rider.geo.json"),
            SoulsCraftTarnishedLegacy.resource("textures/armor/night_rider.png")).build());
    }
}
