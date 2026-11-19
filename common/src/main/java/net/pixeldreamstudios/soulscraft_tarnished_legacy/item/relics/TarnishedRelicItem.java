package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.relics;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;


public final class TarnishedRelicItem extends Item {
    private final String relicId;

    public TarnishedRelicItem(Properties properties, String relicId) {
        super(properties);
        this.relicId = relicId;
    }

    /*
     * Crafting remainder hooks. These deliberately have no @Override: the common module only sees
     * vanilla, but at runtime they override the loader hooks of the same name and signature:
     *   - Fabric:   FabricItem#getRecipeRemainder(ItemStack)
     *   - NeoForge: IItemExtension#hasCraftingRemainingItem(ItemStack) / getCraftingRemainingItem(ItemStack)
     * Renaming them, or a loader renaming its hook, silently disables the "relic loses 1 durability
     * per craft" behaviour, so re-check these after loader updates.
     */

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
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
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
