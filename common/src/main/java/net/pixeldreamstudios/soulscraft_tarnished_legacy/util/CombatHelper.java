package net.pixeldreamstudios.soulscraft_tarnished_legacy.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;

public final class CombatHelper {
    private CombatHelper() {}

    public static boolean isFriendly(Player player, LivingEntity target) {
        if (player.isAlliedTo(target) || target.isAlliedTo(player)) {
            return true;
        }
        return target instanceof OwnableEntity ownable && player.getUUID().equals(ownable.getOwnerUUID());
    }
}
