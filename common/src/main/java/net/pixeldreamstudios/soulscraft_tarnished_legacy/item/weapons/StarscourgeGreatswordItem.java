package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;

public final class StarscourgeGreatswordItem extends TarnishedWeaponItem {
    public StarscourgeGreatswordItem(Tier tier, Properties properties) {
        super(tier, properties, "starscourge_greatsword", "colossal_sword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (!roll(attacker, 0.20F)) {
            return;
        }

        Vec3 towardWielder = attacker.position().subtract(target.position());
        if (towardWielder.lengthSqr() < 0.0001D) {
            return;
        }

        Vec3 pull = towardWielder.normalize().scale(0.70D);
        target.push(pull.x, 0.12D, pull.z);
    }
}
