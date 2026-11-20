package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.relics;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public final class TarnishedRelicItem extends Item {
    private final String relicId;

    public TarnishedRelicItem(Properties properties, String relicId) {
        super(properties);
        this.relicId = relicId;
    }

    // No @Override: these match the Fabric (FabricItem) and NeoForge (IItemExtension) remainder hooks by name.
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return damagedCraftingRemainder(stack);
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.isDamageableItem()
                && stack.getDamageValue() < stack.getMaxDamage();
    }

    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        return damagedCraftingRemainder(stack);
    }

    private ItemStack damagedCraftingRemainder(ItemStack stack) {
        if (!stack.isDamageableItem()) {
            return ItemStack.EMPTY;
        }

        int nextDamage = stack.getDamageValue() + 1;
        if (nextDamage >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        ItemStack remainder = stack.copy();
        remainder.setCount(1);
        remainder.setDamageValue(nextDamage);

        return remainder;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable(translationKey("lore.1"))
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.translatable(translationKey("lore.2"))
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable(
                "tooltip.soulscraft_tarnished_legacy.recovered_from",
                Component.translatable(translationKey("source"))
        ).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    private String translationKey(String suffix) {
        return "relic.soulscraft_tarnished_legacy." + relicId + "." + suffix;
    }
}
