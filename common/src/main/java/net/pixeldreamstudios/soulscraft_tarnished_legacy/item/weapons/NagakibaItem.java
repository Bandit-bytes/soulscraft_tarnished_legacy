package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.effect.BloodLossEffect;

public final class NagakibaItem extends TarnishedWeaponItem {
    public NagakibaItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties, "nagakiba", "katana");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        BloodLossEffect.addBuildup(target, attacker);
    }
}
