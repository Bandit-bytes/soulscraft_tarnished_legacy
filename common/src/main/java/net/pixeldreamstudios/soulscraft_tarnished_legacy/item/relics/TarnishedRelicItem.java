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
