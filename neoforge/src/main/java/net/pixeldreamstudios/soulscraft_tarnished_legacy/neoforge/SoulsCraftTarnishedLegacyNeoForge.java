package net.pixeldreamstudios.soulscraft_tarnished_legacy.neoforge;

import mod.azure.azurelib.common.render.armor.AzArmorRendererRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer.*;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.registry.ItemRegistry;

import static net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy.MOD_ID;

@Mod(MOD_ID)
@EventBusSubscriber(
        modid = SoulsCraftTarnishedLegacy.MOD_ID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class SoulsCraftTarnishedLegacyNeoForge {

    public SoulsCraftTarnishedLegacyNeoForge(IEventBus modEventBus) {
        SoulsCraftTarnishedLegacy.init();
        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void onClientSetup(final FMLClientSetupEvent event) {}

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional event) {
        SoulsCraftTarnishedLegacy.initAzIdentityRegistry();
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        AzArmorRendererRegistry.register(
                NightRiderArmorRenderer::new,
                ItemRegistry.NIGHT_RIDER_HELMET.get(),
                ItemRegistry.NIGHT_RIDER_CHESTPLATE.get(),
                ItemRegistry.NIGHT_RIDER_LEGGINGS.get(),
                ItemRegistry.NIGHT_RIDER_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                BlaiddArmorRenderer::new,
                ItemRegistry.BLAIDD_HELMET.get(),
                ItemRegistry.BLAIDD_CHESTPLATE.get(),
                ItemRegistry.BLAIDD_LEGGINGS.get(),
                ItemRegistry.BLAIDD_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                RoninArmorRenderer::new,
                ItemRegistry.RONIN_HELMET.get(),
                ItemRegistry.RONIN_CHESTPLATE.get(),
                ItemRegistry.RONIN_LEGGINGS.get(),
                ItemRegistry.RONIN_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                CrucibleKnightArmorRenderer::new,
                ItemRegistry.CRUCIBLE_KNIGHT_HELMET.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_CHESTPLATE.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_LEGGINGS.get(),
                ItemRegistry.CRUCIBLE_KNIGHT_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                CrucibleKnightVariantArmorRenderer::new,
                ItemRegistry.CRUCIBLE_TREE_HELMET.get(),
                ItemRegistry.CRUCIBLE_TREE_CHESTPLATE.get(),
                ItemRegistry.CRUCIBLE_TREE_LEGGINGS.get(),
                ItemRegistry.CRUCIBLE_TREE_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                TreeSentinelArmorRenderer::new,
                ItemRegistry.TREE_SENTINEL_HELMET.get(),
                ItemRegistry.TREE_SENTINEL_CHESTPLATE.get(),
                ItemRegistry.TREE_SENTINEL_LEGGINGS.get(),
                ItemRegistry.TREE_SENTINEL_BOOTS.get()
        );

        AzArmorRendererRegistry.register(
                MalikethArmorRenderer::new,
                ItemRegistry.MALIKETH_HELMET.get(),
                ItemRegistry.MALIKETH_CHESTPLATE.get(),
                ItemRegistry.MALIKETH_LEGGINGS.get(),
                ItemRegistry.MALIKETH_BOOTS.get()
        );
    }
}
