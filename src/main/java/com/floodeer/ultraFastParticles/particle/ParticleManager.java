package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.UltraFastParticles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ParticleManager {

    private final Map<UUID, List<ParticleBase>> effects = new HashMap<>();
    private int step;

    public ParticleManager() {
        Bukkit.getScheduler().runTaskTimer(UltraFastParticles.get(), () -> {
            step += 1;
            if (step >= 3000) {
                step = 0;
            }
            tick(step);
        }, 1L, 1L);
    }

    public void addEffect(Player p, ParticleBase effect) {
        effects.computeIfAbsent(p.getUniqueId(), k -> new ArrayList<>()).add(effect);
    }

    public void removeEffects(Player p) {
        effects.remove(p.getUniqueId());
    }

    public void tick(long step) {
        for (List<ParticleBase> list : effects.values()) {
            for (ParticleBase effect : list) {
                effect.update(step);
            }
        }
    }
}