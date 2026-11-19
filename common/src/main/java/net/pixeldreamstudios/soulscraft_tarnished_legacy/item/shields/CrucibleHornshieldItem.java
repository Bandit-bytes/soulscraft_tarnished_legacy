package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public final class CrucibleHornshieldItem extends TarnishedShieldItem {
    private static final double MAX_REPEL_DISTANCE = 4.0D;

    public CrucibleHornshieldItem(Properties properties) {
        super(properties, "crucible_shield", Items.GOLD_INGOT);
    }

    /** Horned Bulwark: blocking a melee attack throws the attacker back. */
    @Override
    public void onBlock(Player player, DamageSource source, float amount) {
        // Direct entity == causing entity means melee, not a projectile.
        if (source.getDirectEntity() instanceof LivingEntity attacker
                && attacker == source.getEntity()
                && attacker.distanceToSqr(player) <= MAX_REPEL_DISTANCE * MAX_REPEL_DISTANCE) {
            attacker.knockback(0.9D, player.getX() - attacker.getX(), player.getZ() - attacker.getZ());
            attacker.hurtMarked = true;
        }
    }
}
