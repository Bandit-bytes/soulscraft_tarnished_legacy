package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class CrucibleKnightArmorItem extends TarnishedArmorItem {
 public CrucibleKnightArmorItem(Type type, Item.Properties properties) {
  super(TarnishedArmorMaterials.CRUCIBLE_KNIGHT, type, properties, "crucible_knight");
 }
 @Override protected void applySetBonus(Player player) {
  player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, true, false, true));
 }
}
