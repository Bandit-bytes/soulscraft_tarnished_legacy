package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public final class TarnishedArmorMaterialRegistry {
    private TarnishedArmorMaterialRegistry() {
    }
    public static Holder<ArmorMaterial> register(String modId, String id, EnumMap<ArmorItem.Type,Integer> protection, int enchantability, Holder<SoundEvent> sound, float toughness, float knockback, Supplier<Ingredient> repair) {
        EnumMap<ArmorItem.Type,Integer> values = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) values.put(type, protection.getOrDefault(type, 0));
        ArmorMaterial material = new ArmorMaterial(values, enchantability, sound, repair, List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(modId, id))), toughness, knockback);
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(modId, id), material);
    }
}
