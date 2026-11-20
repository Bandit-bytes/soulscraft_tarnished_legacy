package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.Level;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.SoulsCraftTarnishedLegacy;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.ArmorRenderProviders;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.client.renderer.TarnishedArmorRendererFactory;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TarnishedArmorItem extends ArmorItem implements GeoItem {
    private static final Map<String, List<SetBonusModifier>> SET_MODIFIERS = new LinkedHashMap<>();

    private final String setId;
    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    private Supplier<Object> renderProvider;

    public TarnishedArmorItem(ArmorMaterial material, Type type, Properties properties, String setId) {
        this(material, type, properties, setId, List.of());
    }

    public TarnishedArmorItem(
            ArmorMaterial material,
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

    // Fabric GeckoLib: GeoItem renderer hook.
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(createArmorRenderProvider());
    }

    // Fabric GeckoLib only; resolved lazily because GeoItem.makeRenderer does not exist on Forge.
    @Override
    public Supplier<Object> getRenderProvider() {
        if (this.renderProvider == null) {
            this.renderProvider = GeoItem.makeRenderer(this);
        }
        return this.renderProvider;
    }

    // No @Override: matches Forge's Item#initializeClient(Consumer<IClientItemExtensions>) by erasure.
    // Forge calls this from the Item constructor, so setId is read lazily when the renderer is first built.
    public void initializeClient(Consumer<Object> consumer) {
        consumer.accept(createArmorRenderProvider());
    }

    private Object createArmorRenderProvider() {
        return ArmorRenderProviders.create(() -> TarnishedArmorRendererFactory.create(this.setId));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".lore")
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy.full_set")
                .withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy." + this.setId + ".bonus")
                .withStyle(ChatFormatting.GRAY));
    }

    protected void tickFullSet(Player player) {
        if (player.tickCount % 20 == 0) {
            applySetBonus(player);
        }
    }

    protected void applySetBonus(Player player) {}

    protected void onBlockedAttack(Player player, DamageSource source, float amount) {}

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
                boolean has = instance.getModifier(bonus.modifier().getId()) != null;
                if (shouldHave && !has) {
                    instance.addTransientModifier(bonus.modifier());
                } else if (!shouldHave && has) {
                    instance.removeModifier(bonus.modifier().getId());
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
            Attribute attribute,
            double amount,
            AttributeModifier.Operation operation
    ) {
        ResourceLocation attributeKey = BuiltInRegistries.ATTRIBUTE.getKey(attribute);
        String attributeName = attributeKey == null ? "attribute" : attributeKey.getPath();
        String name = SoulsCraftTarnishedLegacy.resource("set_bonus/" + setId + "/" + attributeName).toString();
        return new SetBonusModifier(attribute, new AttributeModifier(
                UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8)),
                name,
                amount,
                operation
        ));
    }

    private boolean matches(ItemStack stack) {
        return stack.getItem() instanceof TarnishedArmorItem armor && armor.setId.equals(this.setId);
    }

    public record SetBonusModifier(Attribute attribute, AttributeModifier modifier) {}
}
