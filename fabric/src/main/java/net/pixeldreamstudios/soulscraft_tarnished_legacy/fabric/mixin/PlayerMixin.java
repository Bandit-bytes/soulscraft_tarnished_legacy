package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields.TarnishedShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Vanilla only damages and disables {@code Items.SHIELD}; Forge patches this for every shield, Fabric doesn't.
 */
@Mixin(Player.class)
public abstract class PlayerMixin {
    @WrapOperation(
            method = "hurtCurrentlyUsedShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    private boolean soulscraft_tarnished_legacy$damageTarnishedShields(
            ItemStack useItem,
            Item item,
            Operation<Boolean> original
    ) {
        return original.call(useItem, item) || useItem.getItem() instanceof TarnishedShieldItem;
    }

    @WrapOperation(
            method = "disableShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V")
    )
    private void soulscraft_tarnished_legacy$disableTarnishedShields(
            ItemCooldowns cooldowns,
            Item item,
            int ticks,
            Operation<Void> original
    ) {
        original.call(cooldowns, item, ticks);
        Item used = ((Player) (Object) this).getUseItem().getItem();
        if (used instanceof TarnishedShieldItem) {
            original.call(cooldowns, used, ticks);
        }
    }
}
