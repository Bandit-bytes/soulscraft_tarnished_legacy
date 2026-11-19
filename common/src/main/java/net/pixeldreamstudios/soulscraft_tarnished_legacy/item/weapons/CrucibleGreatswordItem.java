package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Tier;

public final class CrucibleGreatswordItem extends TarnishedWeaponItem {
    public CrucibleGreatswordItem(Tier tier, Properties properties) {
        super(tier, properties, "crucible_greatsword", "greatsword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
        if (roll(attacker, 0.25F)) {
            // knockback() pushes away from (x, z), so pass attacker - target.
            target.knockback(1.25D, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
            target.hurtMarked = true;
        }
    }
}
