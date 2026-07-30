package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class NightRiderArmorItem extends TarnishedArmorItem {
 public NightRiderArmorItem(Type type, Item.Properties properties) {
  super(TarnishedArmorMaterials.NIGHT_RIDER, type, properties, "night_rider");
 }
 @Override protected void applySetBonus(Player player) {
  player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 0, true, false, true));
 }
}
