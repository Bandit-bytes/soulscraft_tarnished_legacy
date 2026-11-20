package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.fabric;

import java.util.function.Supplier;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class ArmorRenderProvidersImpl {
    private ArmorRenderProvidersImpl() {}

    public static Object create(Supplier<GeoArmorRenderer<?>> rendererFactory) {
        return new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            @SuppressWarnings("unchecked")
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(
                    LivingEntity livingEntity,
                    ItemStack itemStack,
                    EquipmentSlot equipmentSlot,
                    HumanoidModel<LivingEntity> original
            ) {
                if (this.renderer == null) {
                    this.renderer = rendererFactory.get();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return (HumanoidModel<LivingEntity>) this.renderer;
            }
        };
    }
}
