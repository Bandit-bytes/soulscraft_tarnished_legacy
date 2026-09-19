package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor;

import java.util.EnumMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TarnishedArmorMaterials {
    private TarnishedArmorMaterials() {}
    private static Holder<ArmorMaterial> create(String id, int boots, int legs, int chest, int helmet, int enchantment, float toughness, float knockback, net.minecraft.core.Holder<net.minecraft.sounds.SoundEvent> sound, net.minecraft.world.item.Item repair) {
        return TarnishedArmorMaterialRegistry.register(SoulsCraftTarnishedLegacy.MOD_ID, id, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, boots); map.put(ArmorItem.Type.LEGGINGS, legs); map.put(ArmorItem.Type.CHESTPLATE, chest); map.put(ArmorItem.Type.HELMET, helmet); map.put(ArmorItem.Type.BODY, chest);
        }), enchantment, sound, toughness, knockback, () -> Ingredient.of(repair));
    }
    public static final Holder<ArmorMaterial> NIGHT_RIDER = create("night_rider",3,6,8,3,10,2.0f,0.10f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.IRON_INGOT);
    public static final Holder<ArmorMaterial> BLAIDD = create("blaidd",3,6,8,3,12,2.5f,0.10f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.IRON_INGOT);
    public static final Holder<ArmorMaterial> RONIN = create("ronin",3,6,8,3,12,2.5f,0.10f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.IRON_INGOT);
    public static final Holder<ArmorMaterial> CRUCIBLE_KNIGHT = create("crucible_knight",4,7,9,4,15,3.5f,0.20f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.GOLD_INGOT);
    public static final Holder<ArmorMaterial> TREE_SENTINEL = create("tree_sentinel",4,7,9,4,14,3.0f,0.20f,SoundEvents.ARMOR_EQUIP_GOLD,Items.GOLD_INGOT);
    public static final Holder<ArmorMaterial> MALIKETH = create("maliketh",4,7,9,4,18,4.0f,0.25f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.NETHERITE_INGOT);
    public static final Holder<ArmorMaterial> RADAHN = create("radahn",4,7,10,4,14,4.0f,0.30f,SoundEvents.ARMOR_EQUIP_NETHERITE,Items.GOLD_INGOT);
}
