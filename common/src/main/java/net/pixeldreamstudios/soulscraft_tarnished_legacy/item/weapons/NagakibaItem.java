package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class NagakibaItem extends TarnishedWeaponItem {

    public NagakibaItem(Tier tier, Properties properties) {
        super(tier, properties, "nagakiba", "katana");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (roll(attacker, 0.20F)) {
            target.addEffect(
                    new MobEffectInstance(MobEffects.WITHER, 100, 0),
                    attacker
            );
        }
    }
}