package net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public final class StarscourgeGreatswordItem extends TarnishedWeaponItem {
    public StarscourgeGreatswordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties, "starscourge_greatsword", "colossal_sword");
    }

    @Override
    protected void applyTrait(LivingEntity target, LivingEntity attacker) {
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (level.isClientSide()) {
            return InteractionResultHolder.sidedSuccess(stack, true);
        }

        if (player instanceof ServerPlayer serverPlayer && StarscourgeSkill.tryActivate(serverPlayer, this, hand)) {
            return InteractionResultHolder.sidedSuccess(stack, false);
        }
        return InteractionResultHolder.fail(stack);
    }
}
