package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.util.client.ClientUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import org.jetbrains.annotations.NotNull;

public class MalikethArmorAnimator extends AzItemAnimator {

    private static final ResourceLocation ANIMATIONS =
            SoulsCraftTarnishedLegacy.resource(
                    "animations/armor/maliketh_armor.animation.json"
            );

    private float smoothedCapeRotation;

    @Override
    public void registerControllers(
            AzAnimationControllerContainer<ItemStack> animationControllerContainer
    ) {
        animationControllerContainer.add(
                AzAnimationController.builder(this, "base_controller").build()
        );
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return ANIMATIONS;
    }

    @Override
    public void setCustomAnimations(ItemStack stack, float partialTick) {
        super.setCustomAnimations(stack, partialTick);

        var model = context().boneCache().getBakedModel();

        if (model == null) {
            return;
        }

        var capeOptional = model.getBone("cape");

        if (capeOptional.isEmpty()) {
            return;
        }

        var cape = capeOptional.get();
        Player player = ClientUtils.getClientPlayer();

        if (player == null) {
            cape.setRotX(0.0F);
            cape.setRotY(0.0F);
            cape.setRotZ(0.0F);
            return;
        }

        ItemStack equippedChest =
                player.getItemBySlot(EquipmentSlot.CHEST);

        if (equippedChest.isEmpty()
                || !ItemStack.isSameItemSameComponents(equippedChest, stack)) {


            cape.setRotX(0.0F);
            cape.setRotY(0.0F);
            cape.setRotZ(0.0F);
            return;
        }

        float h = partialTick;

        double dx =
                Mth.lerp(h, player.xCloakO, player.xCloak)
                        - Mth.lerp(h, player.xo, player.getX());

        double dy =
                Mth.lerp(h, player.yCloakO, player.yCloak)
                        - Mth.lerp(h, player.yo, player.getY());

        double dz =
                Mth.lerp(h, player.zCloakO, player.zCloak)
                        - Mth.lerp(h, player.zo, player.getZ());

        float bodyRotation =
                Mth.rotLerp(
                        h,
                        player.yBodyRotO,
                        player.yBodyRot
                );

        float radians =
                bodyRotation * Mth.DEG_TO_RAD;

        double sin = Mth.sin(radians);
        double cos = -Mth.cos(radians);

        float verticalMotion =
                (float) dy * 10.0F;

        verticalMotion = Mth.clamp(
                verticalMotion,
                -6.0F,
                32.0F
        );

        float backwardSway =
                (float) (dx * sin + dz * cos)
                        * 200.0F;

        backwardSway = Mth.clamp(
                backwardSway,
                0.0F,
                150.0F
        );

        float sidewaysSway =
                (float) (dx * cos - dz * sin)
                        * 100.0F;

        sidewaysSway = Mth.clamp(
                sidewaysSway,
                -20.0F,
                20.0F
        );

        float walkBob =
                Mth.lerp(
                        h,
                        player.oBob,
                        player.bob
                );

        float walkDistance =
                Mth.lerp(
                        h,
                        player.walkDistO,
                        player.walkDist
                );

        verticalMotion +=
                Mth.sin(walkDistance * 6.0F)
                        * 8.0F
                        * walkBob;

        if (player.isCrouching()) {
            verticalMotion += 10.0F;
        }

        float baseX = 0.10F;

        cape.setRotX(
                baseX
                        - backwardSway / 200.0F
                        - verticalMotion / 40.0F
        );

        cape.setRotY(0.0F);
        cape.setRotZ(sidewaysSway / 180.0F);
    }
}