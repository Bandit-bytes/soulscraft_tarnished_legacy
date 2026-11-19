package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;

public class TarnishedShieldItem extends ShieldItem {
    private final String shieldId;
    private final Item repairItem;

    protected TarnishedShieldItem(Properties properties, String shieldId, Item repairItem) {
        super(properties);
        this.shieldId = shieldId;
        this.repairItem = repairItem;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable(
                "tooltip.soulscraft_tarnished_legacy.weapon_class",
                Component.translatable("weapon_class.soulscraft_tarnished_legacy.greatshield")
        ).withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable(translationKey("lore.1"))
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.translatable(translationKey("lore.2"))
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable(
                "tooltip.soulscraft_tarnished_legacy.trait",
                Component.translatable(translationKey("trait.name"))
        ).withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable(translationKey("trait.description"))
                .withStyle(ChatFormatting.GRAY));
    }

    public void onBlock(Player player, DamageSource source, float amount) {}

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(repairItem);
    }

    private String translationKey(String suffix) {
        return "shield.soulscraft_tarnished_legacy." + shieldId + "." + suffix;
    }
}
