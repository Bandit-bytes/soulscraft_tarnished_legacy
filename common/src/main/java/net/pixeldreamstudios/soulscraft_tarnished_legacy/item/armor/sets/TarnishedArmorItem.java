package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer.TarnishedArmorRendererFactory;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TarnishedArmorItem extends ArmorItem implements GeoItem {
    private final String setId;
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public TarnishedArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, String setId) {
        super(material, type, properties);
        this.setId = setId;
    }

    public String getSetId() {
        return this.setId;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
                    @Nullable T livingEntity,
                    ItemStack itemStack,
                    @Nullable EquipmentSlot equipmentSlot,
                    @Nullable HumanoidModel<T> original
            ) {
                if (this.renderer == null) {
                    this.renderer = TarnishedArmorRendererFactory.create(TarnishedArmorItem.this.setId);
                }

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Cape motion is calculated per render frame in TarnishedArmorModel.
        // No keyframed animation controller is needed for the current armor sets.
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".lore")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy.full_set")
                .withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".bonus")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide()
                && getType() == Type.HELMET
                && entity instanceof Player player
                && player.tickCount % 20 == 0
                && hasFullSet(player)) {
            applySetBonus(player);
        }
    }

    protected void applySetBonus(Player player) {}

    protected boolean hasFullSet(LivingEntity entity) {
        return matches(entity.getItemBySlot(EquipmentSlot.HEAD))
                && matches(entity.getItemBySlot(EquipmentSlot.CHEST))
                && matches(entity.getItemBySlot(EquipmentSlot.LEGS))
                && matches(entity.getItemBySlot(EquipmentSlot.FEET));
    }

    private boolean matches(ItemStack stack) {
        return stack.getItem() instanceof TarnishedArmorItem armor && armor.setId.equals(this.setId);
    }
}
