package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public final class ErdtreeGreatshieldItem extends TarnishedShieldItem {
    private static final int ABSORPTION_TICKS = 100;

    public ErdtreeGreatshieldItem(Properties properties) {
        super(properties, "tree_sentinel_shield", Items.GOLD_INGOT);
    }

    /** Golden Bulwark: blocking grants brief Absorption (not refreshed while it's still active). */
    @Override
    public void onBlock(Player player, DamageSource source, float amount) {
        if (!player.hasEffect(MobEffects.ABSORPTION)) {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, ABSORPTION_TICKS, 0, false, false, true));
        }
    }
}
