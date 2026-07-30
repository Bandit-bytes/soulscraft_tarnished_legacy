package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class NightRiderGlaiveItem extends TarnishedWeaponItem {
    public NightRiderGlaiveItem(Tier tier, Properties properties) {
        super(tier, properties, "night_rider_glaive", "glaive");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (attacker.level().isNight() && roll(attacker, 0.30F)) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 0), attacker);
        }
    }
}
