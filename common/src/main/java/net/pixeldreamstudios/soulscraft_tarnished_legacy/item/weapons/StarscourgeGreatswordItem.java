package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class StarscourgeGreatswordItem extends TarnishedWeaponItem {
    private static final int COMBO_WINDOW_TICKS = 28;
    private static final int MIN_STAGE_DELAY_TICKS = 5;
    private static final int ABANDONED_COMBO_COOLDOWN_TICKS = 140;
    private static final int FINISHER_COOLDOWN_TICKS = 200;

    private final Map<UUID, ComboState> comboStates = new HashMap<>();
    private final Map<UUID, Long> skillCooldownEnds = new HashMap<>();
    private final Map<UUID, List<PendingImpact>> pendingImpacts = new HashMap<>();

    public StarscourgeGreatswordItem(Tier tier, Properties properties) {
        super(tier, properties, "starscourge_greatsword", "colossal_sword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (level.isClientSide()) {
            return InteractionResultHolder.sidedSuccess(stack, true);
        }

        long now = level.getGameTime();
        UUID playerId = player.getUUID();
        ComboState state = comboStates.get(playerId);

        if (state != null && now > state.expiresAt()) {
            comboStates.remove(playerId);
            state = null;
        }

        if (state == null && now < skillCooldownEnds.getOrDefault(playerId, 0L)) {
            return InteractionResultHolder.fail(stack);
        }

        if (state != null && now < state.nextInputAt()) {
            return InteractionResultHolder.fail(stack);
        }

        int stage = state == null ? 1 : state.nextStage();
        player.swing(hand, true);
        playSlashCue(level, player, stage);
        queueImpact(playerId, now + (stage == 3 ? 5 : 3), stage);

        if (stage < 3) {
            comboStates.put(playerId, new ComboState(
                    stage + 1,
                    now + COMBO_WINDOW_TICKS,
                    now + MIN_STAGE_DELAY_TICKS
            ));

            skillCooldownEnds.put(playerId, now + ABANDONED_COMBO_COOLDOWN_TICKS);
        } else {
            comboStates.remove(playerId);
            skillCooldownEnds.put(playerId, now + FINISHER_COOLDOWN_TICKS);
            player.getCooldowns().addCooldown(this, FINISHER_COOLDOWN_TICKS);
        }

        return InteractionResultHolder.sidedSuccess(stack, false);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!(level instanceof ServerLevel serverLevel) || !(entity instanceof Player player)) {
            return;
        }

        UUID playerId = player.getUUID();
        List<PendingImpact> impacts = pendingImpacts.get(playerId);
        if (impacts == null || impacts.isEmpty()) {
            return;
        }

        long now = level.getGameTime();
        for (int i = impacts.size() - 1; i >= 0; i--) {
            PendingImpact impact = impacts.get(i);
            if (now >= impact.executeAt()) {
                performImpact(serverLevel, player, impact.stage());
                impacts.remove(i);
            }
        }

        if (impacts.isEmpty()) {
            pendingImpacts.remove(playerId);
        }
    }

    private void queueImpact(UUID playerId, long executeAt, int stage) {
        pendingImpacts.computeIfAbsent(playerId, ignored -> new ArrayList<>())
                .add(new PendingImpact(executeAt, stage));
    }

    private static void playSlashCue(Level level, Player player, int stage) {
        float pitch = switch (stage) {
            case 1 -> 0.86F;
            case 2 -> 1.0F;
            default -> 0.72F;
        };

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP,
                SoundSource.PLAYERS,
                stage == 3 ? 1.35F : 1.0F,
                pitch
        );
    }

    private static void performImpact(ServerLevel level, Player player, int stage) {
        Vec3 forward = horizontalLook(player);
        Vec3 right = new Vec3(-forward.z, 0.0D, forward.x);
        Vec3 origin = player.position().add(0.0D, 0.15D, 0.0D);
        Set<Integer> hitEntities = new HashSet<>();

        switch (stage) {
            case 1 -> {

                strike(level, player, origin.add(forward.scale(2.0D)), 0.85D, 5.0F, false, hitEntities);
                strike(level, player, origin.add(forward.scale(3.6D)), 0.90D, 5.0F, false, hitEntities);
                strike(level, player, origin.add(forward.scale(5.2D)), 0.95D, 5.0F, false, hitEntities);
            }
            case 2 -> {

                for (int i = -2; i <= 2; i++) {
                    double side = i * 0.95D;
                    double distance = 3.5D + Math.abs(i) * 0.35D;
                    Vec3 position = origin.add(forward.scale(distance)).add(right.scale(side));
                    strike(level, player, position, 0.95D, 6.0F, false, hitEntities);
                }
            }
            case 3 -> {

                Vec3 center = origin.add(forward.scale(3.0D));
                strike(level, player, center, 1.35D, 9.0F, true, hitEntities);

                for (int i = 0; i < 8; i++) {
                    double angle = Math.toRadians(i * 45.0D);
                    Vec3 ray = new Vec3(Math.cos(angle), 0.0D, Math.sin(angle));
                    strike(level, player, center.add(ray.scale(2.6D)), 0.95D, 9.0F, true, hitEntities);
                }

                level.playSound(
                        null,
                        center.x,
                        center.y,
                        center.z,
                        SoundEvents.BEACON_POWER_SELECT,
                        SoundSource.PLAYERS,
                        1.7F,
                        0.72F
                );
            }
            default -> { }
        }
    }

    private static void strike(
            ServerLevel level,
            Player player,
            Vec3 position,
            double radius,
            float damage,
            boolean launch,
            Set<Integer> hitEntities
    ) {
        spawnLightColumn(level, position, launch);

        AABB hitBox = new AABB(
                position.x - radius,
                position.y - 0.8D,
                position.z - radius,
                position.x + radius,
                position.y + 3.2D,
                position.z + radius
        );

        for (Entity entity : level.getEntities(player, hitBox, Entity::isAlive)) {
            if (!(entity instanceof LivingEntity target)
                    || target == player
                    || player.isAlliedTo(target)
                    || !hitEntities.add(target.getId())) {
                continue;
            }

            target.hurt(level.damageSources().playerAttack(player), damage);

            if (launch) {
                Vec3 away = target.position().subtract(position);
                Vec3 horizontal = new Vec3(away.x, 0.0D, away.z);
                if (horizontal.lengthSqr() < 0.0001D) {
                    horizontal = horizontalLook(player);
                } else {
                    horizontal = horizontal.normalize();
                }

                target.push(horizontal.x * 0.42D, 0.58D, horizontal.z * 0.42D);
                target.hasImpulse = true;
            }
        }
    }

    private static void spawnLightColumn(ServerLevel level, Vec3 position, boolean finisher) {
        int layers = finisher ? 13 : 10;
        double step = finisher ? 0.32D : 0.30D;

        for (int i = 0; i < layers; i++) {
            double y = position.y + 0.25D + i * step;
            level.sendParticles(
                    ParticleTypes.END_ROD,
                    position.x,
                    y,
                    position.z,
                    finisher ? 3 : 2,
                    0.10D,
                    0.06D,
                    0.10D,
                    0.008D
            );
        }

        level.sendParticles(
                ParticleTypes.WAX_ON,
                position.x,
                position.y + (finisher ? 2.4D : 1.8D),
                position.z,
                finisher ? 10 : 6,
                0.28D,
                0.75D,
                0.28D,
                0.02D
        );
    }

    private static Vec3 horizontalLook(Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 horizontal = new Vec3(look.x, 0.0D, look.z);
        return horizontal.lengthSqr() < 0.0001D
                ? new Vec3(0.0D, 0.0D, 1.0D)
                : horizontal.normalize();
    }

    private record ComboState(int nextStage, long expiresAt, long nextInputAt) {}

    private record PendingImpact(long executeAt, int stage) {}
}
