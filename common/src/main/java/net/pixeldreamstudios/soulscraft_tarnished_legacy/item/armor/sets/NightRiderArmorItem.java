package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.List;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class NightRiderArmorItem extends TarnishedArmorItem {
    private static final String SET_ID = "night_rider";
    // Night Vision starts flashing below 200 ticks; stay above that while refreshing every second.
    private static final int NIGHT_VISION_TICKS = 260;

    public NightRiderArmorItem(Type type, Item.Properties properties) {
        super(TarnishedArmorMaterials.NIGHT_RIDER, type, properties, SET_ID, List.of(
                // Equivalent to Speed I, but immune to milk and doesn't clash with potions.
                setModifier(SET_ID, Attributes.MOVEMENT_SPEED, 0.20D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        ));
    }

    @Override
    protected void applySetBonus(Player player) {
        if (player.level().isNight()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, NIGHT_VISION_TICKS, 0, true, false, true));
        }
    }
}
