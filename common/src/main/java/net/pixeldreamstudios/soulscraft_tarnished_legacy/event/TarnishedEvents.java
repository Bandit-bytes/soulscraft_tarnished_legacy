package net.pixeldreamstudios.soulscraft_tarnished_legacy.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.world.entity.player.Player;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.armor.sets.TarnishedArmorItem;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.shields.TarnishedShieldItem;
import net.pixeldreamstudios.soulscraft_tarnished_legacy.item.weapons.StarscourgeSkill;

public final class TarnishedEvents {
    private TarnishedEvents() {}

    public static void init() {
        TickEvent.SERVER_POST.register(StarscourgeSkill::tick);
        LifecycleEvent.SERVER_STOPPED.register(server -> StarscourgeSkill.clear());
        PlayerEvent.PLAYER_QUIT.register(player -> StarscourgeSkill.forget(player.getUUID()));

        TickEvent.PLAYER_POST.register(player -> {
            if (!player.level().isClientSide()) {
                TarnishedArmorItem.tickPlayer(player);
            }
        });

        EntityEvent.LIVING_HURT.register((entity, source, amount) -> {
            if (entity instanceof Player player
                    && !player.level().isClientSide()
                    && amount > 0.0F
                    && player.isDamageSourceBlocked(source)) {
                if (player.getUseItem().getItem() instanceof TarnishedShieldItem shield) {
                    shield.onBlock(player, source, amount);
                }
                TarnishedArmorItem.onPlayerBlocked(player, source, amount);
            }
            return EventResult.pass();
        });
    }
}
