package net.pixeldreamstudios.soulscraft_tarnished_legacy.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.effect.BloodLossEffect;

public final class EffectRegistry {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(SoulsCraftTarnishedLegacy.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> BLOOD_LOSS =
            EFFECTS.register("blood_loss", BloodLossEffect::new);

    private EffectRegistry() {}

    public static void init() {
        EFFECTS.register();
    }
}
