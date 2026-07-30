package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;
public class TreeSentinelArmorItem extends TarnishedArmorItem {

 public TreeSentinelArmorItem(Type type, Item.Properties properties) {
  super(TarnishedArmorMaterials.TREE_SENTINEL, type, properties, "tree_sentinel"); }

 @Override protected void applySetBonus(Player player) {
  player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0, true, false, true));
 }
}
