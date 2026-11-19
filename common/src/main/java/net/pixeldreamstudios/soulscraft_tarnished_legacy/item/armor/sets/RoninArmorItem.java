package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class RoninArmorItem extends TarnishedArmorItem {
    private static final int RIPOSTE_TICKS = 80;

    public RoninArmorItem(Type type, Properties properties) {
        super(TarnishedArmorMaterials.RONIN, type, properties, "ronin");
    }

    @Override
    protected void onBlockedAttack(Player player, DamageSource source, float amount) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, RIPOSTE_TICKS, 0, false, false, true));
    }
}
