package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class TreeSentinelHalberdItem extends TarnishedWeaponItem {
    public TreeSentinelHalberdItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties, "tree_sentinel_halberd", "halberd");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (attacker.getHealth() >= attacker.getMaxHealth() * 0.80F && roll(attacker, 0.20F)) {
            attacker.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 0, false, true, true));
        }
    }
}
