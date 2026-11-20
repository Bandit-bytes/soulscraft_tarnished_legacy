package net.pixeldreamstudios.soulscraft_tarnished_legacy.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields.TarnishedShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 1.20.1 mobs with axes disable shields through their own copy of the logic, again only for {@code Items.SHIELD}.
 */
@Mixin(Mob.class)
public abstract class MobMixin {
    @WrapOperation(
            method = "maybeDisableShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    private boolean soulscraft_tarnished_legacy$axesDisableTarnishedShields(
            ItemStack playerItem,
            Item item,
            Operation<Boolean> original
    ) {
        return original.call(playerItem, item) || playerItem.getItem() instanceof TarnishedShieldItem;
    }

    @WrapOperation(
            method = "maybeDisableShield",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V")
    )
    private void soulscraft_tarnished_legacy$cooldownTarnishedShields(
            ItemCooldowns cooldowns,
            Item item,
            int ticks,
            Operation<Void> original,
            @Local(argsOnly = true, ordinal = 1) ItemStack playerItem
    ) {
        original.call(cooldowns, item, ticks);
        if (playerItem.getItem() instanceof TarnishedShieldItem) {
            original.call(cooldowns, playerItem.getItem(), ticks);
        }
    }
}
