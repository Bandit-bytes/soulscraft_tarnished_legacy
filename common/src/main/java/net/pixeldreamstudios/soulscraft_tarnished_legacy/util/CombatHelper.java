package net.pixeldreamstudios.soulscraft_tarnished_legacy.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;

public final class CombatHelper {
    private CombatHelper() {}

    /**
     * True if {@code target} should be spared by {@code player}'s area attacks: teammates, and pets
     * or other entities the player owns. {@code player.isAlliedTo} alone misses tamed animals.
     */
    public static boolean isFriendly(Player player, LivingEntity target) {
        if (player.isAlliedTo(target) || target.isAlliedTo(player)) {
            return true;
        }
        return target instanceof OwnableEntity ownable && player.getUUID().equals(ownable.getOwnerUUID());
    }
}
