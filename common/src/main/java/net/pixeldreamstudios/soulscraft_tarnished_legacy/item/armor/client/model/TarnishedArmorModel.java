package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.model;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;

public final class TarnishedArmorModel extends GeoModel<TarnishedArmorItem> {
    private static final ResourceLocation EMPTY_ANIMATION =
            SoulsCraftTarnishedLegacy.resource("animations/armor/empty.animation.json");

    // Per-tick smoothing rates (the old per-frame 0.09 / 0.08 at ~60 FPS is roughly this per tick).
    private static final float TREE_SENTINEL_ROTATION_RATE = 0.25F;
    private static final float TREE_SENTINEL_LIFT_RATE = 0.22F;

    private final ResourceLocation modelResource;
    private final ResourceLocation textureResource;
    private final CapeStyle capeStyle;
    /**
     * Smoothing state per wearer, keyed by entity id. GeckoLib's armor instance id can't be used:
     * it is {@code (long) Math.pow(entityId, 7)}, which saturates for every entity id >= 512 and
     * would make all wearers share one state.
     */
    private final Map<Integer, CapeSmoothingState> capeStates = new HashMap<>();

    public TarnishedArmorModel(ResourceLocation modelResource, ResourceLocation textureResource, CapeStyle capeStyle) {
        this.modelResource = modelResource;
        this.textureResource = textureResource;
        this.capeStyle = capeStyle;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation getModelResource(TarnishedArmorItem animatable) {
        return this.modelResource;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation getTextureResource(TarnishedArmorItem animatable) {
        return this.textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(TarnishedArmorItem animatable) {
        return EMPTY_ANIMATION;
    }

    @Override
    public void setCustomAnimations(
            TarnishedArmorItem animatable,
            long instanceId,
            AnimationState<TarnishedArmorItem> animationState
    ) {
        // Radahn's Blockbench model was authored with an additional head-space transform.
        // The tracking bone is now vanilla-centered in the geo; this visual child keeps the
        // intended silhouette while trimming the oversized helmet slightly.
        GeoBone radahnHeadVisual = getBone("radahnHeadVisual").orElse(null);
        if (radahnHeadVisual != null) {
            radahnHeadVisual.updateScale(0.86F, 0.86F, 0.86F);
        }

        GeoBone cape = getBone("cape").orElse(null);
        if (cape == null) {
            return;
        }

        resetCape(cape);

        if (this.capeStyle == CapeStyle.NONE) {
            return;
        }

        Player player = animationState.getData(DataTickets.ENTITY) instanceof Player wearer ? wearer : null;
        ItemStack renderedStack = animationState.getData(DataTickets.ITEMSTACK);
        EquipmentSlot slot = animationState.getData(DataTickets.EQUIPMENT_SLOT);

        // The same model renders every armor slot; only the chest render drives the cape.
        if (slot != EquipmentSlot.CHEST || renderedStack == null) {
            return;
        }

        if (player == null
                || !ItemStack.isSameItemSameComponents(player.getItemBySlot(EquipmentSlot.CHEST), renderedStack)) {
            if (player != null) {
                this.capeStates.remove(player.getId());
            }
            return;
        }

        float partialTick = Mth.clamp(animationState.getPartialTick(), 0.0F, 1.0F);

        switch (this.capeStyle) {
            case BLAIDD -> animateBlaiddCape(cape, player, partialTick);
            case MALIKETH -> animateMalikethCape(cape, player, partialTick);
            case TREE_SENTINEL -> animateTreeSentinelCape(cape, player, partialTick);
            case NONE -> { }
        }
    }

    private static void resetCape(GeoBone cape) {
        cape.setRotX(0.0F);
        cape.setRotY(0.0F);
        cape.setRotZ(0.0F);
        cape.setPosX(0.0F);
        cape.setPosY(0.0F);
        cape.setPosZ(0.0F);
    }

    private static void animateBlaiddCape(GeoBone cape, Player player, float partialTick) {
        double deltaX = Mth.lerp(partialTick, player.xCloakO, player.xCloak)
                - Mth.lerp(partialTick, player.xo, player.getX());
        double deltaY = Mth.lerp(partialTick, player.yCloakO, player.yCloak)
                - Mth.lerp(partialTick, player.yo, player.getY());
        double deltaZ = Mth.lerp(partialTick, player.zCloakO, player.zCloak)
                - Mth.lerp(partialTick, player.zo, player.getZ());

        float bodyRotation = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot);
        float bodyRotationRadians = bodyRotation * Mth.DEG_TO_RAD;
        double sin = Mth.sin(bodyRotationRadians);
        double cos = -Mth.cos(bodyRotationRadians);

        float backwardMovement = Mth.clamp((float)(deltaX * sin + deltaZ * cos) * 100.0F, 0.0F, 45.0F);
        float sidewaysMovement = Mth.clamp((float)(deltaX * cos - deltaZ * sin) * 100.0F, -15.0F, 15.0F);
        float verticalMovement = Mth.clamp((float)deltaY * 10.0F, -6.0F, 18.0F);
        float walkAmount = Mth.lerp(partialTick, player.oBob, player.bob);
        float walkDistance = Mth.lerp(partialTick, player.walkDistO, player.walkDist);
        float walkBob = Mth.sin(walkDistance * 6.0F) * 0.035F * walkAmount;
        float crouchLift = player.isCrouching() ? 0.12F : 0.0F;

        float backwardRotation = backwardMovement / 60.0F
                + verticalMovement / 100.0F
                + walkBob
                + crouchLift;
        backwardRotation += (float)player.getDeltaMovement().horizontalDistance() * 2.2F;

        if (player.isSprinting()) {
            backwardRotation += 0.18F;
        }

        backwardRotation = Mth.clamp(backwardRotation, 0.0F, 0.45F);
        float sideRotation = Mth.clamp(sidewaysMovement / 130.0F, -0.12F, 0.12F);

        cape.setRotX(-backwardRotation);
        cape.setRotZ(sideRotation);
    }

    private static void animateMalikethCape(GeoBone cape, Player player, float partialTick) {
        double deltaX = Mth.lerp(partialTick, player.xCloakO, player.xCloak)
                - Mth.lerp(partialTick, player.xo, player.getX());
        double deltaY = Mth.lerp(partialTick, player.yCloakO, player.yCloak)
                - Mth.lerp(partialTick, player.yo, player.getY());
        double deltaZ = Mth.lerp(partialTick, player.zCloakO, player.zCloak)
                - Mth.lerp(partialTick, player.zo, player.getZ());

        float bodyRotation = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot);
        float radians = bodyRotation * Mth.DEG_TO_RAD;
        double sin = Mth.sin(radians);
        double cos = -Mth.cos(radians);

        float verticalMotion = Mth.clamp((float)deltaY * 10.0F, -6.0F, 32.0F);
        float backwardSway = Mth.clamp((float)(deltaX * sin + deltaZ * cos) * 200.0F, 0.0F, 150.0F);
        float sidewaysSway = Mth.clamp((float)(deltaX * cos - deltaZ * sin) * 100.0F, -20.0F, 20.0F);
        float walkBob = Mth.lerp(partialTick, player.oBob, player.bob);
        float walkDistance = Mth.lerp(partialTick, player.walkDistO, player.walkDist);

        verticalMotion += Mth.sin(walkDistance * 6.0F) * 8.0F * walkBob;
        if (player.isCrouching()) {
            verticalMotion += 10.0F;
        }

        cape.setRotX(0.10F - backwardSway / 200.0F - verticalMotion / 40.0F);
        cape.setRotZ(sidewaysSway / 180.0F);
    }

    private void animateTreeSentinelCape(GeoBone cape, Player player, float partialTick) {
        float horizontalSpeed = (float)player.getDeltaMovement().horizontalDistance();
        float targetRotation = 0.0F;
        float targetLift = 0.0F;

        if (player.isSprinting()) {
            targetRotation = 0.50F;
            targetLift = 4.0F;
        } else if (horizontalSpeed > 0.005F) {
            float walkStrength = Mth.clamp(horizontalSpeed / 0.12F, 0.0F, 1.0F);
            targetRotation = 0.30F * walkStrength;
            targetLift = 2.0F * walkStrength;
        }

        if (player.isCrouching()) {
            targetRotation += 0.025F;
            targetLift += 0.25F;
        }

        targetRotation = Mth.clamp(targetRotation, 0.0F, 0.50F);
        targetLift = Mth.clamp(targetLift, 0.0F, 4.0F);

        if (this.capeStates.size() > 64) {
            // Wearers that left render distance never report back; drop stale entries.
            this.capeStates.clear();
        }
        CapeSmoothingState state = this.capeStates.computeIfAbsent(player.getId(), ignored -> new CapeSmoothingState());
        // Smooth over elapsed game time rather than per render call, so the cape moves at the same
        // speed regardless of frame rate or how many times the player is drawn in a frame.
        float time = player.tickCount + partialTick;
        float elapsedTicks = Float.isNaN(state.lastTime) ? 0.0F : Mth.clamp(time - state.lastTime, 0.0F, 5.0F);
        state.lastTime = time;
        state.rotation = Mth.lerp(smoothingFactor(TREE_SENTINEL_ROTATION_RATE, elapsedTicks), state.rotation, targetRotation);
        state.lift = Mth.lerp(smoothingFactor(TREE_SENTINEL_LIFT_RATE, elapsedTicks), state.lift, targetLift);

        float walkDistance = Mth.lerp(partialTick, player.walkDistO, player.walkDist);
        float walkAmount = Mth.lerp(partialTick, player.oBob, player.bob);
        float movementStrength = Mth.clamp(horizontalSpeed / 0.12F, 0.0F, 1.0F);
        float gentleBob = Mth.sin(walkDistance * 5.0F) * 0.015F * walkAmount * movementStrength;

        cape.setRotX(-(state.rotation + gentleBob));
        cape.setPosY(state.lift);
    }

    /** Fraction of the remaining distance covered after {@code elapsedTicks} at {@code ratePerTick}. */
    private static float smoothingFactor(float ratePerTick, float elapsedTicks) {
        return 1.0F - (float) Math.pow(1.0F - ratePerTick, elapsedTicks);
    }

    private static final class CapeSmoothingState {
        private float rotation;
        private float lift;
        private float lastTime = Float.NaN;
    }
}
