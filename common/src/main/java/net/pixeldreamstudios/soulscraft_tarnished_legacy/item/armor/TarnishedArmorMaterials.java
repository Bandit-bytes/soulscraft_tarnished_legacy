package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor;

import com.google.common.base.Suppliers;
import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public enum TarnishedArmorMaterials implements ArmorMaterial {
    NIGHT_RIDER("night_rider", 32, 3, 6, 8, 3, 10, 2.0F, 0.10F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.IRON_INGOT),
    BLAIDD("blaidd", 35, 3, 6, 8, 3, 12, 2.5F, 0.10F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.IRON_INGOT),
    RONIN("ronin", 35, 3, 6, 8, 3, 12, 2.5F, 0.10F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.IRON_INGOT),
    CRUCIBLE_KNIGHT("crucible_knight", 39, 4, 7, 9, 4, 15, 3.5F, 0.20F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.GOLD_INGOT),
    CRUCIBLE_TREE("crucible_tree", 39, 4, 7, 9, 4, 15, 3.5F, 0.20F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.GOLD_INGOT),
    TREE_SENTINEL("tree_sentinel", 41, 4, 7, 9, 4, 14, 3.0F, 0.20F, SoundEvents.ARMOR_EQUIP_GOLD, () -> Items.GOLD_INGOT),
    MALIKETH("maliketh", 45, 4, 7, 9, 4, 18, 4.0F, 0.25F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.NETHERITE_INGOT),
    RADAHN("radahn", 48, 4, 7, 10, 4, 14, 4.0F, 0.30F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.GOLD_INGOT);

    private final String name;
    private final int durabilityMultiplier;
    private final int boots;
    private final int leggings;
    private final int chestplate;
    private final int helmet;
    private final int enchantmentValue;
    private final float toughness;
    private final float knockbackResistance;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;

    TarnishedArmorMaterials(
            String id,
            int durabilityMultiplier,
            int boots,
            int leggings,
            int chestplate,
            int helmet,
            int enchantmentValue,
            float toughness,
            float knockbackResistance,
            SoundEvent equipSound,
            Supplier<Item> repairItem
    ) {
        this.name = SoulsCraftTarnishedLegacy.MOD_ID + ":" + id;
        this.durabilityMultiplier = durabilityMultiplier;
        this.boots = boots;
        this.leggings = leggings;
        this.chestplate = chestplate;
        this.helmet = helmet;
        this.enchantmentValue = enchantmentValue;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.equipSound = equipSound;
        this.repairIngredient = Suppliers.memoize(() -> Ingredient.of(repairItem.get()));
    }

    /** Same per-slot base durability as vanilla's armor materials. */
    public static int durability(ArmorItem.Type type, int multiplier) {
        int base = switch (type) {
            case HELMET -> 11;
            case CHESTPLATE -> 16;
            case LEGGINGS -> 15;
            case BOOTS -> 13;
        };
        return base * multiplier;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return durability(type, this.durabilityMultiplier);
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> this.helmet;
            case CHESTPLATE -> this.chestplate;
            case LEGGINGS -> this.leggings;
            case BOOTS -> this.boots;
        };
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
