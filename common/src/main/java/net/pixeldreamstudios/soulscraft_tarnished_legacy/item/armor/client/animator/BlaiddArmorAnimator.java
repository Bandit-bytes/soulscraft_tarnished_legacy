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

public class BlaiddArmorAnimator extends AzItemAnimator {

    private static final ResourceLocation ANIMATIONS =
            SoulsCraftTarnishedLegacy.resource(
                    "animations/blaidd_armor.animation.json"
            );

    private static final float RESTING_X_ROTATION = 0.0F;
    private static final float MAX_BACKWARD_ROTATION = 0.45F;
    private static final float MAX_SIDE_ROTATION = 0.12F;

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

        cape.setRotX(RESTING_X_ROTATION);
        cape.setRotY(0.0F);
        cape.setRotZ(0.0F);

        Player player = ClientUtils.getClientPlayer();

        if (player == null) {
            return;
        }

        ItemStack equippedChest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (equippedChest != stack) {
            return;
        }

        float partial = Mth.clamp(partialTick, 0.0F, 1.0F);

        double cloakX = Mth.lerp(
                partial,
                player.xCloakO,
                player.xCloak
        );

        double cloakY = Mth.lerp(
                partial,
                player.yCloakO,
                player.yCloak
        );

        double cloakZ = Mth.lerp(
                partial,
                player.zCloakO,
                player.zCloak
        );

        double playerX = Mth.lerp(
                partial,
                player.xo,
                player.getX()
        );

        double playerY = Mth.lerp(
                partial,
                player.yo,
                player.getY()
        );

        double playerZ = Mth.lerp(
                partial,
                player.zo,
                player.getZ()
        );

        double deltaX = cloakX - playerX;
        double deltaY = cloakY - playerY;
        double deltaZ = cloakZ - playerZ;

        float bodyRotation = Mth.rotLerp(
                partial,
                player.yBodyRotO,
                player.yBodyRot
        );

        float bodyRotationRadians = bodyRotation * Mth.DEG_TO_RAD;

        double sin = Mth.sin(bodyRotationRadians);
        double cos = -Mth.cos(bodyRotationRadians);

        float backwardMovement =
                (float) (deltaX * sin + deltaZ * cos) * 100.0F;

        backwardMovement = Mth.clamp(
                backwardMovement,
                0.0F,
                45.0F
        );

        float sidewaysMovement =
                (float) (deltaX * cos - deltaZ * sin) * 100.0F;

        sidewaysMovement = Mth.clamp(
                sidewaysMovement,
                -15.0F,
                15.0F
        );

        float verticalMovement = (float) deltaY * 10.0F;

        verticalMovement = Mth.clamp(
                verticalMovement,
                -6.0F,
                18.0F
        );

        float walkAmount = Mth.lerp(
                partial,
                player.oBob,
                player.bob
        );

        float walkDistance = Mth.lerp(
                partial,
                player.walkDistO,
                player.walkDist
        );

        float walkBob =
                Mth.sin(walkDistance * 6.0F)
                        * 0.035F
                        * walkAmount;

        float crouchLift = player.isCrouching() ? 0.12F : 0.0F;

        float backwardRotation =
                backwardMovement / 60.0F
                        + verticalMovement / 100.0F
                        + walkBob
                        + crouchLift;


        float horizontalSpeed =
                (float) player.getDeltaMovement().horizontalDistance();

        float movementLift = horizontalSpeed * 2.2F;

        if (player.isSprinting()) {
            movementLift += 0.18F;
        }

        backwardRotation += movementLift;

        backwardRotation = Mth.clamp(
                backwardRotation,
                0.0F,
                MAX_BACKWARD_ROTATION
        );

        float sideRotation = Mth.clamp(
                sidewaysMovement / 130.0F,
                -MAX_SIDE_ROTATION,
                MAX_SIDE_ROTATION
        );

        cape.setRotX(RESTING_X_ROTATION - backwardRotation);
        cape.setRotY(0.0F);
        cape.setRotZ(sideRotation);
    }
}