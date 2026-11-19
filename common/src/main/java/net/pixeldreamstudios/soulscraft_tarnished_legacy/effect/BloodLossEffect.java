package net.pixeldreamstudios.soulscraft_tarnished_legacy.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.EffectRegistry;
import org.joml.Vector3f;

public final class BloodLossEffect extends MobEffect {
    public static final int STACKS_TO_BURST = 4;
    public static final int BUILDUP_DURATION_TICKS = 100;
    public static final float BURST_MAX_HEALTH_FRACTION = 0.12F;
    public static final float BURST_MIN_DAMAGE = 3.0F;
    public static final float BURST_MAX_DAMAGE = 30.0F;

    private static final DustParticleOptions BLOOD =
            new DustParticleOptions(new Vector3f(0.55F, 0.0F, 0.02F), 1.6F);

    public BloodLossEffect() {
        super(MobEffectCategory.HARMFUL, 0x8A0303);
    }

    public static void addBuildup(LivingEntity target, LivingEntity attacker) {
        if (!(target.level() instanceof ServerLevel level)) {
            return;
        }

        Holder<MobEffect> effect = EffectRegistry.BLOOD_LOSS;
        MobEffectInstance current = target.getEffect(effect);
        int stacks = current == null ? 1 : current.getAmplifier() + 2;

        if (stacks < STACKS_TO_BURST) {
            target.addEffect(new MobEffectInstance(effect, BUILDUP_DURATION_TICKS, stacks - 1, false, true, true), attacker);
            return;
        }

        target.removeEffect(effect);

        float damage = Mth.clamp(target.getMaxHealth() * BURST_MAX_HEALTH_FRACTION, BURST_MIN_DAMAGE, BURST_MAX_DAMAGE);
        target.invulnerableTime = 0;
        target.hurt(target.damageSources().indirectMagic(attacker, attacker), damage);

        level.sendParticles(BLOOD, target.getX(), target.getY(0.6D), target.getZ(),
                40, target.getBbWidth() * 0.4D, target.getBbHeight() * 0.3D, target.getBbWidth() * 0.4D, 0.15D);
        level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.PLAYERS, 1.2F, 0.55F);
    }
}
