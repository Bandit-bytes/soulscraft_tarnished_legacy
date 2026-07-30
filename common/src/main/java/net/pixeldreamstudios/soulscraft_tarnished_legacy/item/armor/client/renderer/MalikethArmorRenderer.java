package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer;

import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import net.minecraft.resources.ResourceLocation;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator.MalikethArmorAnimator;

public class MalikethArmorRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = SoulsCraftTarnishedLegacy.resource(
            "geo/armor/maliketh_armor.geo.json"
    );

    private static final ResourceLocation TEX = SoulsCraftTarnishedLegacy.resource(
            "textures/armor/maliketh_armor.png"
    );

    public MalikethArmorRenderer() {
        super(
        AzArmorRendererConfig.builder(GEO, TEX)
                .setAnimatorProvider(MalikethArmorAnimator::new)
                .build()
        );
    }
}
