package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets;

import java.util.List;
import net.minecraft.ChatFormatting;
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

public class TarnishedArmorItem extends ArmorItem {
    private final String setId;
    public TarnishedArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, String setId) { super(material, type, properties); this.setId=setId; }
    @Override public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy."+setId+".lore").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy.full_set").withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("armor_set.soulscraft_tarnished_legacy."+setId+".bonus").withStyle(ChatFormatting.GRAY));
    }
    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (!level.isClientSide() && getType()==Type.HELMET && entity instanceof Player player && player.tickCount%20==0 && hasFullSet(player)) applySetBonus(player);
    }
    protected void applySetBonus(Player player) {}
    protected boolean hasFullSet(LivingEntity e) { return matches(e.getItemBySlot(EquipmentSlot.HEAD))&&matches(e.getItemBySlot(EquipmentSlot.CHEST))&&matches(e.getItemBySlot(EquipmentSlot.LEGS))&&matches(e.getItemBySlot(EquipmentSlot.FEET)); }
    private boolean matches(ItemStack s) { return s.getItem() instanceof TarnishedArmorItem armor && armor.setId.equals(setId); }
}
