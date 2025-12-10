package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.UltraFastParticles;
import com.floodeer.ultraFastParticles.math.ParticleEquation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class ParticleBase {

    private final Player player;
    private final ParticleEquation equationResolver;

    public ParticleBase(Player player, ParticleEquation equationResolver) {
        this.player = player;
        this.equationResolver = equationResolver;
    }

    public void update(long step) {

        Bukkit.getScheduler().runTaskAsynchronously(UltraFastParticles.get(), () -> {
            ParticleEquation.EquationResult res = equationResolver.calculate(step);
            Bukkit.getScheduler().runTask(UltraFastParticles.get(), () -> onRender(player, res));
        });
    }

    public abstract void onRender(Player p, ParticleEquation.EquationResult res);
}