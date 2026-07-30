package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class CrucibleKnightVariantArmorItem extends TarnishedArmorItem {

 public CrucibleKnightVariantArmorItem(Type type, Item.Properties properties) {
  super(TarnishedArmorMaterials.CRUCIBLE_KNIGHT, type, properties, "crucible_tree");
 }
 @Override protected void applySetBonus(Player player) {
  player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 0, true, false, true));
 }
}
