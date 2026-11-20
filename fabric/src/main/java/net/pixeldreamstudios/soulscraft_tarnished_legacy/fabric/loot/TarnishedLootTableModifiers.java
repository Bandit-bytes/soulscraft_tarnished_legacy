package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric.loot;

import java.util.Map;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TarnishedLootTableModifiers {
    private static final Map<ResourceLocation, ResourceLocation> INJECTIONS = Map.ofEntries(
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
            Map.entry(EntityType.GHAST.getDefaultLootTable(), injection("soul_in_a_bottle"))
    );

    private TarnishedLootTableModifiers() {
    }

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!source.isBuiltin()) {
                return;
            }

            ResourceLocation injection = INJECTIONS.get(id);
            if (injection != null) {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootTableReference.lootTableReference(injection))
                );
            }
        });
    }

    private static ResourceLocation injection(String relicId) {
        return SoulsCraftTarnishedLegacy.resource("injections/" + relicId);
    }
}
