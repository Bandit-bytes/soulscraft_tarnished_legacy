package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.List;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;

public class BlaiddArmorItem extends TarnishedArmorItem {
    private static final String SET_ID = "blaidd";

    public BlaiddArmorItem(Type type, Item.Properties properties) {
        super(TarnishedArmorMaterials.BLAIDD, type, properties, SET_ID, List.of(
                setModifier(SET_ID, Attributes.ATTACK_DAMAGE, 3.0D, AttributeModifier.Operation.ADDITION)
        ));
    }
}
