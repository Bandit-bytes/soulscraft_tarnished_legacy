package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class BlaiddRoyalGreatswordItem extends TarnishedWeaponItem {
    public BlaiddRoyalGreatswordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties, "blaidd_royal_greatsword", "colossal_greatsword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (roll(attacker, 0.20F)) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1), attacker);
        }
    }
}
