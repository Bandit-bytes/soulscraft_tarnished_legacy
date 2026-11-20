package net.pixeldreamstudios.soulscraft_tarnished_legacy.forge.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TarnishedLootModifiers {
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SoulsCraftTarnishedLegacy.MOD_ID);

    static {
        SERIALIZERS.register("add_table", AddTableLootModifier.CODEC);
    }

    private TarnishedLootModifiers() {}

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
    }
}
