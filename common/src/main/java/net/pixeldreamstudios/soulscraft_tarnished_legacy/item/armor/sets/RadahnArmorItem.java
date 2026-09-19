package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public final class RadahnArmorItem extends TarnishedArmorItem {
    public RadahnArmorItem(Type type, Item.Properties properties) {
        super(TarnishedArmorMaterials.RADAHN, type, properties, "radahn");
    }

    @Override
    protected void applySetBonus(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 0, true, false, true));
    }
}
