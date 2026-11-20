package net.pixeldreamstudios.soulscraft_tarnished_legacy.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.TarnishedArmorMaterials;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.*;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.relics.TarnishedRelicItem;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields.CrucibleHornshieldItem;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields.ErdtreeGreatshieldItem;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons.*;

public final class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    SoulsCraftTarnishedLegacy.MOD_ID,
                    Registries.ITEM
            );

    private ItemRegistry() {
    }

    private static Item.Properties armorProperties(
            ArmorItem.Type type,
            int durabilityMultiplier,
            Rarity rarity
    ) {
        return new Item.Properties()
                .durability(TarnishedArmorMaterials.durability(type, durabilityMultiplier))
                .rarity(rarity)
                .arch$tab(TabRegistry.TARNISHED_LEGACY_TAB);
    }

    private static Item.Properties weaponProperties(Rarity rarity) {
        return new Item.Properties()
                .stacksTo(1)
                .rarity(rarity)
                .arch$tab(TabRegistry.TARNISHED_LEGACY_TAB);
    }

    private static Item.Properties shieldProperties(int durability, Rarity rarity) {
        return new Item.Properties()
                .durability(durability)
                .rarity(rarity)
                .arch$tab(TabRegistry.TARNISHED_LEGACY_TAB);
    }

    private static Item.Properties relicProperties(Rarity rarity) {
        return new Item.Properties()
                .durability(4)
                .rarity(rarity)
                .arch$tab(TabRegistry.TARNISHED_LEGACY_TAB);
    }

    public static final RegistrySupplier<Item> NIGHT_RIDER_HELMET =
            ITEMS.register(
                    "night_rider_helmet",
                    () -> new NightRiderArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 32, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> NIGHT_RIDER_CHESTPLATE =
            ITEMS.register(
                    "night_rider_chestplate",
                    () -> new NightRiderArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 32, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> NIGHT_RIDER_LEGGINGS =
            ITEMS.register(
                    "night_rider_leggings",
                    () -> new NightRiderArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 32, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> NIGHT_RIDER_BOOTS =
            ITEMS.register(
                    "night_rider_boots",
                    () -> new NightRiderArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 32, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> BLAIDD_HELMET =
            ITEMS.register(
                    "blaidd_helmet",
                    () -> new BlaiddArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> BLAIDD_CHESTPLATE =
            ITEMS.register(
                    "blaidd_chestplate",
                    () -> new BlaiddArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> BLAIDD_LEGGINGS =
            ITEMS.register(
                    "blaidd_leggings",
                    () -> new BlaiddArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> BLAIDD_BOOTS =
            ITEMS.register(
                    "blaidd_boots",
                    () -> new BlaiddArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> RONIN_HELMET =
            ITEMS.register(
                    "ronin_helmet",
                    () -> new RoninArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> RONIN_CHESTPLATE =
            ITEMS.register(
                    "ronin_chestplate",
                    () -> new RoninArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> RONIN_LEGGINGS =
            ITEMS.register(
                    "ronin_leggings",
                    () -> new RoninArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> RONIN_BOOTS =
            ITEMS.register(
                    "ronin_boots",
                    () -> new RoninArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 35, Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_KNIGHT_HELMET =
            ITEMS.register(
                    "crucible_knight_helmet",
                    () -> new CrucibleKnightArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_KNIGHT_CHESTPLATE =
            ITEMS.register(
                    "crucible_knight_chestplate",
                    () -> new CrucibleKnightArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_KNIGHT_LEGGINGS =
            ITEMS.register(
                    "crucible_knight_leggings",
                    () -> new CrucibleKnightArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_KNIGHT_BOOTS =
            ITEMS.register(
                    "crucible_knight_boots",
                    () -> new CrucibleKnightArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_TREE_HELMET =
            ITEMS.register(
                    "crucible_knight_variant_helmet",
                    () -> new CrucibleKnightVariantArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_TREE_CHESTPLATE =
            ITEMS.register(
                    "crucible_knight_variant_chestplate",
                    () -> new CrucibleKnightVariantArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_TREE_LEGGINGS =
            ITEMS.register(
                    "crucible_knight_variant_leggings",
                    () -> new CrucibleKnightVariantArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_TREE_BOOTS =
            ITEMS.register(
                    "crucible_knight_variant_boots",
                    () -> new CrucibleKnightVariantArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 39, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_HELMET =
            ITEMS.register(
                    "tree_sentinel_helmet",
                    () -> new TreeSentinelArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 41, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_CHESTPLATE =
            ITEMS.register(
                    "tree_sentinel_chestplate",
                    () -> new TreeSentinelArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 41, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_LEGGINGS =
            ITEMS.register(
                    "tree_sentinel_leggings",
                    () -> new TreeSentinelArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 41, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_BOOTS =
            ITEMS.register(
                    "tree_sentinel_boots",
                    () -> new TreeSentinelArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 41, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> MALIKETH_HELMET =
            ITEMS.register(
                    "maliketh_helmet",
                    () -> new MalikethArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 45, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> MALIKETH_CHESTPLATE =
            ITEMS.register(
                    "maliketh_chestplate",
                    () -> new MalikethArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 45, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> MALIKETH_LEGGINGS =
            ITEMS.register(
                    "maliketh_leggings",
                    () -> new MalikethArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 45, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> MALIKETH_BOOTS =
            ITEMS.register(
                    "maliketh_boots",
                    () -> new MalikethArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 45, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> RADAHN_HELMET =
            ITEMS.register(
                    "radahn_helmet",
                    () -> new RadahnArmorItem(
                            ArmorItem.Type.HELMET,
                            armorProperties(ArmorItem.Type.HELMET, 48, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> RADAHN_CHESTPLATE =
            ITEMS.register(
                    "radahn_chestplate",
                    () -> new RadahnArmorItem(
                            ArmorItem.Type.CHESTPLATE,
                            armorProperties(ArmorItem.Type.CHESTPLATE, 48, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> RADAHN_LEGGINGS =
            ITEMS.register(
                    "radahn_leggings",
                    () -> new RadahnArmorItem(
                            ArmorItem.Type.LEGGINGS,
                            armorProperties(ArmorItem.Type.LEGGINGS, 48, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> RADAHN_BOOTS =
            ITEMS.register(
                    "radahn_boots",
                    () -> new RadahnArmorItem(
                            ArmorItem.Type.BOOTS,
                            armorProperties(ArmorItem.Type.BOOTS, 48, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> NIGHT_CAVALRY_INSIGNIA =
            ITEMS.register(
                    "night_cavalry_insignia",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.RARE),
                            "night_cavalry_insignia"
                    )
            );

    public static final RegistrySupplier<Item> ROYAL_WOLF_SHARD =
            ITEMS.register(
                    "royal_wolf_shard",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.RARE),
                            "royal_wolf_shard"
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_SCALE =
            ITEMS.register(
                    "crucible_scale",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.EPIC),
                            "crucible_scale"
                    )
            );

    public static final RegistrySupplier<Item> SOUL_IN_A_BOTTLE =
            ITEMS.register(
                    "soul_in_a_bottle",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.EPIC),
                            "soul_in_a_bottle"
                    )
            );

    public static final RegistrySupplier<Item> ERDTREE_CREST =
            ITEMS.register(
                    "erdtree_crest",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.EPIC),
                            "erdtree_crest"
                    )
            );

    public static final RegistrySupplier<Item> DESTINED_DEATH_FRAGMENT =
            ITEMS.register(
                    "destined_death_fragment",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.EPIC).fireResistant(),
                            "destined_death_fragment"
                    )
            );

    public static final RegistrySupplier<Item> FALLINGSTAR_REMNANT =
            ITEMS.register(
                    "fallingstar_remnant",
                    () -> new TarnishedRelicItem(
                            relicProperties(Rarity.EPIC),
                            "fallingstar_remnant"
                    )
            );

    public static final RegistrySupplier<Item> NIGHT_RIDER_GLAIVE =
            ITEMS.register(
                    "night_rider_glaive",
                    () -> new NightRiderGlaiveItem(
                            TarnishedWeaponMaterials.NIGHTRIDER_TIER,
                            5,
                            -2.8F,
                            weaponProperties(Rarity.RARE)
                    )
            );

    public static final RegistrySupplier<Item> BLAIDD_ROYAL_GREATSWORD =
            ITEMS.register(
                    "blaidd_royal_greatsword",
                    () -> new BlaiddRoyalGreatswordItem(
                            TarnishedWeaponMaterials.BLAIDD_TIER,
                            7,
                            -3.0F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_GREATSWORD =
            ITEMS.register(
                    "crucible_greatsword",
                    () -> new CrucibleGreatswordItem(
                            TarnishedWeaponMaterials.CRUCIBLE_TIER,
                            7,
                            -2.9F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_HALBERD =
            ITEMS.register(
                    "tree_sentinel_halberd",
                    () -> new TreeSentinelHalberdItem(
                            TarnishedWeaponMaterials.TREE_SENTINEL_TIER,
                            6,
                            -2.8F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> MALIKETH_BLACK_BLADE =
            ITEMS.register(
                    "maliketh_black_blade",
                    () -> new MalikethBlackBladeItem(
                            TarnishedWeaponMaterials.MALIKETH_TIER,
                            8,
                            -3.1F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> NAGAKIBA =
            ITEMS.register(
                    "nagakiba",
                    () -> new NagakibaItem(
                            TarnishedWeaponMaterials.SAMURAI_TIER,
                            7,
                            -2.4F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> STARSCOURGE_GREATSWORD =
            ITEMS.register(
                    "starscourge_greatsword",
                    () -> new StarscourgeGreatswordItem(
                            TarnishedWeaponMaterials.RADAHN_TIER,
                            9,
                            -3.2F,
                            weaponProperties(Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> CRUCIBLE_SHIELD =
            ITEMS.register(
                    "crucible_shield",
                    () -> new CrucibleHornshieldItem(
                            shieldProperties(672, Rarity.EPIC)
                    )
            );

    public static final RegistrySupplier<Item> TREE_SENTINEL_SHIELD =
            ITEMS.register(
                    "tree_sentinel_shield",
                    () -> new ErdtreeGreatshieldItem(
                            shieldProperties(720, Rarity.EPIC)
                    )
            );

    public static void init() {
        ITEMS.register();
    }
}
