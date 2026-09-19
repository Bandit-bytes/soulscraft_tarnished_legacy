package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric.loot;

import java.util.Map;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TarnishedLootTableModifiers {

    private static final ResourceKey<LootTable> GHAST_LOOT =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    ResourceLocation.withDefaultNamespace("entities/ghast")
            );

    private static final Map<ResourceKey<LootTable>, ResourceKey<LootTable>> INJECTIONS = Map.ofEntries(
            Map.entry(BuiltInLootTables.RUINED_PORTAL, injection("night_cavalry_insignia")),
            Map.entry(BuiltInLootTables.PILLAGER_OUTPOST, injection("night_cavalry_insignia")),
            Map.entry(BuiltInLootTables.WOODLAND_MANSION, injection("royal_wolf_shard")),
            Map.entry(BuiltInLootTables.ANCIENT_CITY, injection("royal_wolf_shard")),
            Map.entry(BuiltInLootTables.STRONGHOLD_CORRIDOR, injection("crucible_scale")),
            Map.entry(BuiltInLootTables.STRONGHOLD_CROSSING, injection("crucible_scale")),
            Map.entry(BuiltInLootTables.BASTION_TREASURE, injection("erdtree_crest")),
            Map.entry(BuiltInLootTables.DESERT_PYRAMID, injection("erdtree_crest")),
            Map.entry(BuiltInLootTables.END_CITY_TREASURE, injection("destined_death_fragment")),
            Map.entry(BuiltInLootTables.ANCIENT_CITY_ICE_BOX, injection("destined_death_fragment")),
            Map.entry(BuiltInLootTables.ABANDONED_MINESHAFT, injection("fallingstar_remnant")),
            Map.entry(GHAST_LOOT, injection("soul_in_a_bottle"))
    );

    private TarnishedLootTableModifiers() {
    }

    public static void init() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }

            ResourceKey<LootTable> injection = INJECTIONS.get(key);
            if (injection != null) {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(NestedLootTable.lootTableReference(injection))
                );
            }
        });
    }

    private static ResourceKey<LootTable> injection(String relicId) {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                SoulsCraftTarnishedLegacy.resource("injections/" + relicId)
        );
    }
}
