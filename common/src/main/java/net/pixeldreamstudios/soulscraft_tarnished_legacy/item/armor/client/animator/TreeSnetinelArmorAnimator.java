package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.animator;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import mod.azure.azurelib.common.util.client.ClientUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import org.jetbrains.annotations.NotNull;

public class TreeSnetinelArmorAnimator extends AzItemAnimator {

    private static final ResourceLocation ANIMATIONS =
            SoulsCraftTarnishedLegacy.resource(
                    "animations/tree_sentinel_armor.animation.json"
            );


    private float smoothedRotation = 0.0F;
    private float smoothedLift = 0.0F;

    private static final float WALK_ROTATION = 0.30F;
    private static final float SPRINT_ROTATION = 0.50F;

    private static final float WALK_LIFT = 2.0F;
    private static final float SPRINT_LIFT = 4.0F;

    /*
     * Lower values make the cape heavier and slower.
     * Higher values make it react faster.
     */
    private static final float ROTATION_SMOOTHING = 0.09F;
    private static final float LIFT_SMOOTHING = 0.08F;

    @Override
    public void registerControllers(
            AzAnimationControllerContainer<ItemStack> animationControllerContainer
    ) {
        animationControllerContainer.add(
                AzAnimationController.builder(
                        this,
                        "base_controller"
                ).build()
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


        cape.setRotX(0.0F);
        cape.setRotY(0.0F);
        cape.setRotZ(0.0F);
        cape.setPosY(0.0F);

        Player player = ClientUtils.getClientPlayer();

        if (player == null) {
            return;
        }

        if (player.getItemBySlot(EquipmentSlot.CHEST) != stack) {
            return;
        }

        float horizontalSpeed =
                (float) player.getDeltaMovement().horizontalDistance();

        float targetRotation = 0.0F;
        float targetLift = 0.0F;

        if (player.isSprinting()) {

            targetRotation = SPRINT_ROTATION;
            targetLift = SPRINT_LIFT;
        } else if (horizontalSpeed > 0.005F) {

            float walkStrength = Mth.clamp(
                    horizontalSpeed / 0.12F,
                    0.0F,
                    1.0F
            );

            targetRotation = WALK_ROTATION * walkStrength;
            targetLift = WALK_LIFT * walkStrength;
        }

        if (player.isCrouching()) {
            targetRotation += 0.025F;
            targetLift += 0.25F;
        }

        targetRotation = Mth.clamp(
                targetRotation,
                0.0F,
                SPRINT_ROTATION
        );

        targetLift = Mth.clamp(
                targetLift,
                0.0F,
                SPRINT_LIFT
        );


        smoothedRotation = Mth.lerp(
                ROTATION_SMOOTHING,
                smoothedRotation,
                targetRotation
        );

        smoothedLift = Mth.lerp(
                LIFT_SMOOTHING,
                smoothedLift,
                targetLift
        );

        float walkDistance = Mth.lerp(
                partialTick,
                player.walkDistO,
                player.walkDist
        );

        float walkAmount = Mth.lerp(
                partialTick,
                player.oBob,
                player.bob
        );

        float movementStrength = Mth.clamp(
                horizontalSpeed / 0.12F,
                0.0F,
                1.0F
        );

        float gentleBob =
                Mth.sin(walkDistance * 5.0F)
                        * 0.015F
                        * walkAmount
                        * movementStrength;

        cape.setRotX(-(smoothedRotation + gentleBob));
        cape.setPosY(smoothedLift);
    }
}