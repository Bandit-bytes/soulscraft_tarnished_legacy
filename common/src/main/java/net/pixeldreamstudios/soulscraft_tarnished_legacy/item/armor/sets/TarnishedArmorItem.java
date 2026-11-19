package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer.TarnishedArmorRendererFactory;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TarnishedArmorItem extends ArmorItem implements GeoItem {
    /** Attribute modifiers granted by each set's full-set bonus, keyed by set id. */
    private static final Map<String, List<SetBonusModifier>> SET_MODIFIERS = new LinkedHashMap<>();

    private final String setId;
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public TarnishedArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, String setId) {
        this(material, type, properties, setId, List.of());
    }

    public TarnishedArmorItem(
            Holder<ArmorMaterial> material,
            Type type,
            Properties properties,
            String setId,
            List<SetBonusModifier> setBonusModifiers
    ) {
        super(material, type, properties);
        this.setId = setId;
        SET_MODIFIERS.putIfAbsent(setId, List.copyOf(setBonusModifiers));
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
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".lore")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy.full_set")
                .withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".bonus")
                .withStyle(ChatFormatting.GRAY));
    }

    /**
     * Called every server tick while {@code player} wears the complete set. By default this applies
     * {@link #applySetBonus(Player)} once per second.
     */
    protected void tickFullSet(Player player) {
        if (player.tickCount % 20 == 0) {
            applySetBonus(player);
        }
    }

    /** Periodic (once per second) full-set bonus, typically a short ambient effect. */
    protected void applySetBonus(Player player) {}

    /** Called when a player wearing the complete set blocks an attack with a shield. */
    protected void onBlockedAttack(Player player, DamageSource source, float amount) {}

    /**
     * Server-side per-tick driver (called from a player tick event, so it also runs if the
     * armor was just removed, dropped or destroyed). Keeps set-bonus attribute modifiers in sync
     * with what the player is wearing and runs the active set's tick logic.
     */
    public static void tickPlayer(Player player) {
        TarnishedArmorItem activeSet = getFullSet(player);
        String activeId = activeSet == null ? null : activeSet.setId;

        for (Map.Entry<String, List<SetBonusModifier>> entry : SET_MODIFIERS.entrySet()) {
            boolean shouldHave = entry.getKey().equals(activeId);
            for (SetBonusModifier bonus : entry.getValue()) {
                AttributeInstance instance = player.getAttribute(bonus.attribute());
                if (instance == null) {
                    continue;
                }
                boolean has = instance.getModifier(bonus.modifier().id()) != null;
                if (shouldHave && !has) {
                    instance.addTransientModifier(bonus.modifier());
                } else if (!shouldHave && has) {
                    instance.removeModifier(bonus.modifier().id());
                }
            }
        }

        if (activeSet != null) {
            activeSet.tickFullSet(player);
        }
    }

    public static void onPlayerBlocked(Player player, DamageSource source, float amount) {
        TarnishedArmorItem activeSet = getFullSet(player);
        if (activeSet != null) {
            activeSet.onBlockedAttack(player, source, amount);
        }
    }

    /** @return the chest piece of the complete set the entity is wearing, or null if none. */
    @Nullable
    public static TarnishedArmorItem getFullSet(LivingEntity entity) {
        if (!(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof TarnishedArmorItem chest)) {
            return null;
        }
        return chest.matches(entity.getItemBySlot(EquipmentSlot.HEAD))
                && chest.matches(entity.getItemBySlot(EquipmentSlot.LEGS))
                && chest.matches(entity.getItemBySlot(EquipmentSlot.FEET))
                ? chest
                : null;
    }

    protected static SetBonusModifier setModifier(
            String setId,
            Holder<Attribute> attribute,
            double amount,
            AttributeModifier.Operation operation
    ) {
        String attributeName = attribute.unwrapKey()
                .map(key -> key.location().getPath())
                .orElse("attribute");
        return new SetBonusModifier(attribute, new AttributeModifier(
                SoulsCraftTarnishedLegacy.resource("set_bonus/" + setId + "/" + attributeName),
                amount,
                operation
        ));
    }

    private boolean matches(ItemStack stack) {
        return stack.getItem() instanceof TarnishedArmorItem armor && armor.setId.equals(this.setId);
    }

    public record SetBonusModifier(Holder<Attribute> attribute, AttributeModifier modifier) {}
}
