package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.forge;

import java.util.function.Supplier;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class ArmorRenderProvidersImpl {
    private ArmorRenderProvidersImpl() {}

    public static Object create(Supplier<GeoArmorRenderer<?>> rendererFactory) {
        return new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(
                    LivingEntity livingEntity,
                    ItemStack itemStack,
                    EquipmentSlot equipmentSlot,
                    HumanoidModel<?> original
            ) {
                if (this.renderer == null) {
                    this.renderer = rendererFactory.get();
                }

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        };
    }
}
