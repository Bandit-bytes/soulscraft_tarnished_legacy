package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.util.CombatHelper;

/**
 * Full set: "Gravity Crash". Landing after a fall of {@link #MIN_FALL_DISTANCE}+ blocks releases a
 * shockwave that damages and launches nearby enemies.
 */
public final class RadahnArmorItem extends TarnishedArmorItem {
    private static final float MIN_FALL_DISTANCE = 4.0F;
    private static final double RADIUS = 4.0D;
    private static final float MIN_DAMAGE = 4.0F;
    private static final float MAX_DAMAGE = 14.0F;

    /** Fall distance from each player's last airborne tick. Server thread only. */
    private static final Map<Player, Float> LAST_AIRBORNE_FALL = new WeakHashMap<>();

    public RadahnArmorItem(Type type, Item.Properties properties) {
        super(TarnishedArmorMaterials.RADAHN, type, properties, "radahn");
    }

    @Override
    protected void tickFullSet(Player player) {
        super.tickFullSet(player);

        if (!player.onGround()) {
            // Record every airborne tick (including 0 after water/slow-falling resets the fall),
            // because vanilla clears fallDistance on landing before this tick runs.
            LAST_AIRBORNE_FALL.put(player, player.fallDistance);
            return;
        }

        Float fall = LAST_AIRBORNE_FALL.remove(player);
        if (fall != null && fall >= MIN_FALL_DISTANCE && player.level() instanceof ServerLevel level) {
            gravityCrash(level, player, fall);
        }
    }

    private static void gravityCrash(ServerLevel level, Player player, float fallDistance) {
        float damage = Mth.clamp(fallDistance, MIN_DAMAGE, MAX_DAMAGE);
        Vec3 center = player.position();
        AABB area = player.getBoundingBox().inflate(RADIUS, 1.0D, RADIUS);

        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, area, LivingEntity::isAlive)) {
            if (target == player
                    || CombatHelper.isFriendly(player, target)
                    || target.distanceToSqr(center) > RADIUS * RADIUS) {
                continue;
            }

            target.hurt(level.damageSources().playerAttack(player), damage);

            Vec3 away = new Vec3(target.getX() - center.x, 0.0D, target.getZ() - center.z);
            away = away.lengthSqr() < 0.0001D ? Vec3.ZERO : away.normalize();
            target.push(away.x * 0.6D, 0.45D, away.z * 0.6D);
            target.hurtMarked = true;
        }

        level.sendParticles(ParticleTypes.EXPLOSION, center.x, center.y + 0.2D, center.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * 15.0D);
            level.sendParticles(ParticleTypes.CLOUD,
                    center.x + Math.cos(angle) * 1.5D, center.y + 0.1D, center.z + Math.sin(angle) * 1.5D,
                    1, 0.0D, 0.0D, 0.0D, 0.0D);
            level.sendParticles(ParticleTypes.PORTAL,
                    center.x + Math.cos(angle) * RADIUS * 0.75D, center.y + 0.2D, center.z + Math.sin(angle) * RADIUS * 0.75D,
                    3, 0.1D, 0.1D, 0.1D, 0.4D);
        }
        level.playSound(null, center.x, center.y, center.z,
                SoundEvents.MACE_SMASH_GROUND_HEAVY, SoundSource.PLAYERS, 1.2F, 0.7F);
    }
}
