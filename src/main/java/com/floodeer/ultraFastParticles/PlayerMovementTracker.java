package com.floodeer.ultraFastParticles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class PlayerMovementTracker implements Listener {

    private static final Set<UUID> MOVING = ConcurrentHashMap.newKeySet();
    private static final Map<UUID, Long> LAST_MOVE = new ConcurrentHashMap<>();

    private static final long TIMEOUT_MS = 100;

    public static boolean isMoving(Player player) {
        Long last = LAST_MOVE.get(player.getUniqueId());
        if (last == null) return false;

        if ((System.nanoTime() - last) / 1_000_000 > TIMEOUT_MS) {
            MOVING.remove(player.getUniqueId());
            LAST_MOVE.remove(player.getUniqueId());
            return false;
        }

        return MOVING.contains(player.getUniqueId());
    }

    @EventHandler(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent e) {

        if (e.getFrom().getX() == e.getTo().getX()
                && e.getFrom().getY() == e.getTo().getY()
                && e.getFrom().getZ() == e.getTo().getZ()) {
            return;
        }

        UUID id = e.getPlayer().getUniqueId();
        MOVING.add(id);
        LAST_MOVE.put(id, System.nanoTime());
    }
}
