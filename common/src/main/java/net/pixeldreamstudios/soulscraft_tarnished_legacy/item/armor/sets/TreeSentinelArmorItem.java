package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class TreeSentinelArmorItem extends TarnishedArmorItem {
    private static final int ABSORPTION_TICKS = 200;

    public TreeSentinelArmorItem(Type type, Item.Properties properties) {
        super(TarnishedArmorMaterials.TREE_SENTINEL, type, properties, "tree_sentinel");
    }

    @Override
    protected void applySetBonus(Player player) {
        // Re-adding Absorption refills the hearts every time (since 1.20.2), so only grant a new
        // instance once the previous one has run out: the hearts renew every 10 seconds rather
        // than every second.
        if (!player.hasEffect(MobEffects.ABSORPTION)) {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, ABSORPTION_TICKS, 0, true, false, true));
        }
    }
}
