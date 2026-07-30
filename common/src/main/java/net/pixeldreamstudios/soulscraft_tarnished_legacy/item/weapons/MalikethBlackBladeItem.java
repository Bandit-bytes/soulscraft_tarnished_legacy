package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class MalikethBlackBladeItem extends TarnishedWeaponItem {
    public MalikethBlackBladeItem(Tier tier, Properties properties) {
        super(tier, properties, "maliketh_black_blade", "colossal_sword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (roll(attacker, 0.20F)) {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0), attacker);
        }
    }
}
