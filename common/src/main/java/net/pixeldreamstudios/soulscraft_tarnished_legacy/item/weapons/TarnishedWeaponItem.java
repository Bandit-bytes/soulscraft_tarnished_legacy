package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public abstract class TarnishedWeaponItem extends SwordItem {
    private final String weaponId;
    private final String weaponClass;

    protected TarnishedWeaponItem(Tier tier, Properties properties, String weaponId, String weaponClass) {
        super(tier, properties);
        this.weaponId = weaponId;
        this.weaponClass = weaponClass;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable(
                "tooltip.soulscraft_tarnished_legacy.weapon_class",
                Component.translatable("weapon_class.soulscraft_tarnished_legacy." + weaponClass)
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

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean hit = super.hurtEnemy(stack, target, attacker);
        if (!target.level().isClientSide() && target.isAlive()) {
            applyTrait(target, attacker);
        }
        return hit;
    }

    protected boolean roll(LivingEntity attacker, float chance) {
        return attacker.level().getRandom().nextFloat() < chance;
    }

    protected abstract void applyTrait(LivingEntity target, LivingEntity attacker);

    private String translationKey(String suffix) {
        return "weapon.soulscraft_tarnished_legacy." + weaponId + "." + suffix;
    }
}
