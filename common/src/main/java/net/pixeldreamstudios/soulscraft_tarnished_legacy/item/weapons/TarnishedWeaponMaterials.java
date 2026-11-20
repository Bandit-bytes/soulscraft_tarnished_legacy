package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import java.util.function.Supplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public final class TarnishedWeaponMaterials implements Tier {
    private static final int DIAMOND_LEVEL = 3;
    private static final int NETHERITE_LEVEL = 4;

    public static final Tier NIGHTRIDER_TIER = new TarnishedWeaponMaterials(
            1450, 10.0F, 4.0F, 18, DIAMOND_LEVEL,
            () -> Ingredient.of(Items.IRON_INGOT));

    public static final Tier BLAIDD_TIER = new TarnishedWeaponMaterials(
            1700, 11.0F, 5.0F, 16, DIAMOND_LEVEL,
            () -> Ingredient.of(Items.DIAMOND));

    public static final Tier CRUCIBLE_TIER = new TarnishedWeaponMaterials(
            1900, 9.0F, 5.0F, 14, DIAMOND_LEVEL,
            () -> Ingredient.of(Items.GOLD_INGOT));

    public static final Tier TREE_SENTINEL_TIER = new TarnishedWeaponMaterials(
            1800, 10.0F, 4.5F, 16, DIAMOND_LEVEL,
            () -> Ingredient.of(Items.GOLD_INGOT));

    public static final Tier MALIKETH_TIER = new TarnishedWeaponMaterials(
            2031, 10.0F, 6.0F, 10, NETHERITE_LEVEL,
            () -> Ingredient.of(Items.NETHERITE_SCRAP));

    public static final Tier SAMURAI_TIER = new TarnishedWeaponMaterials(
            1561, 10.0F, 4.0F, 18, DIAMOND_LEVEL,
            () -> Ingredient.of(Items.IRON_INGOT));

    public static final Tier RADAHN_TIER = new TarnishedWeaponMaterials(
            2200, 9.0F, 6.0F, 12, NETHERITE_LEVEL,
            () -> Ingredient.of(Items.GOLD_INGOT));

    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final int level;
    private final Supplier<Ingredient> repairIngredient;

    private TarnishedWeaponMaterials(
            int uses,
            float speed,
            float damage,
            int enchantmentValue,
            int level,
            Supplier<Ingredient> repairIngredient
    ) {
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.level = level;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getUses() { return uses; }
    @Override public float getSpeed() { return speed; }
    @Override public float getAttackDamageBonus() { return damage; }
    @Override public int getLevel() { return level; }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public @NotNull Ingredient getRepairIngredient() { return repairIngredient.get(); }
}
