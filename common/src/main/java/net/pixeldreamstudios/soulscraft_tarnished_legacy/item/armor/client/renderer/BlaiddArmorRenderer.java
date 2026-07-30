package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator.BlaiddArmorAnimator;

public class BlaiddArmorRenderer extends AzArmorRenderer {
    public BlaiddArmorRenderer() { super(
            AzArmorRendererConfig.builder(SoulsCraftTarnishedLegacy.resource(
                    "geo/armor/blaidd_armor.geo.json"),
            SoulsCraftTarnishedLegacy.resource(
                    "textures/armor/blaidd_armor.png"))
            .setAnimatorProvider(BlaiddArmorAnimator::new)
            .build());
    }
}
