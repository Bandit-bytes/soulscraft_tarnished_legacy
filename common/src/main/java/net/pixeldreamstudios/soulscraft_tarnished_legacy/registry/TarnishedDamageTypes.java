package net.pixeldreamstudios.soulscraft_tarnished_legacy.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;

public final class TarnishedDamageTypes {
    public static final ResourceKey<DamageType> BLOOD_LOSS = key("blood_loss");
    public static final ResourceKey<DamageType> GRAVITY_CRASH = key("gravity_crash");
    public static final ResourceKey<DamageType> STARSCOURGE_LIGHT = key("starscourge_light");

    private TarnishedDamageTypes() {}

    public static DamageSource source(ResourceKey<DamageType> type, Entity attacker) {
        return new DamageSource(
                attacker.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type),
                attacker
        );
    }

    private static ResourceKey<DamageType> key(String path) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, SoulsCraftTarnishedLegacy.resource(path));
    }
}
