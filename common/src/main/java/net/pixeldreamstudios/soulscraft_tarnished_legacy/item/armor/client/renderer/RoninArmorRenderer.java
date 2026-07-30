package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator.BlaiddArmorAnimator;

public class RoninArmorRenderer extends AzArmorRenderer {
    public RoninArmorRenderer() { super(
            AzArmorRendererConfig.builder(SoulsCraftTarnishedLegacy.resource(
                    "geo/armor/ronin_armor.geo.json"),
            SoulsCraftTarnishedLegacy.resource(
                    "textures/armor/ronin_armor.png"))
            .setAnimatorProvider(BlaiddArmorAnimator::new)
            .build());
    }
}
