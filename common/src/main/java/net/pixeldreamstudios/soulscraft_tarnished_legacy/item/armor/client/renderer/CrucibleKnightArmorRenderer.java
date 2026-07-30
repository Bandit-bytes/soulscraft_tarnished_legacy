package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public class CrucibleKnightArmorRenderer extends AzArmorRenderer {
    public CrucibleKnightArmorRenderer() { super(AzArmorRendererConfig.builder(SoulsCraftTarnishedLegacy.resource("geo/armor/crucible_knight_armor.geo.json"),
            SoulsCraftTarnishedLegacy.resource("textures/armor/crucible_knight_armor.png")).build());
    }
}
