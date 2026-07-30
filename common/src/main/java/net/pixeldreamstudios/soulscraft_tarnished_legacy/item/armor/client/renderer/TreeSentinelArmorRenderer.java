package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator.TreeSnetinelArmorAnimator;

public class TreeSentinelArmorRenderer extends AzArmorRenderer {

    public TreeSentinelArmorRenderer() {
        super(AzArmorRendererConfig.builder(SoulsCraftTarnishedLegacy.resource(
                "geo/armor/tree_sentinel_armor.geo.json"),
            SoulsCraftTarnishedLegacy.resource("textures/armor/tree_sentinel.png"))
                .setAnimatorProvider(TreeSnetinelArmorAnimator::new)
                .build());
    }
}
