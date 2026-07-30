package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class RoninArmorItem extends TarnishedArmorItem {
 public RoninArmorItem(Type type, Properties properties) {
  super(TarnishedArmorMaterials.RONIN, type, properties, "ronin");
 }
 @Override protected void applySetBonus(Player player) {
  player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, true, false, true));
 }
}
