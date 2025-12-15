package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.PlayerMovementTracker;
import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.particle.types.CycloneEffect;
import com.floodeer.ultraFastParticles.particle.types.HelixEffect;
import com.floodeer.ultraFastParticles.particle.types.LillyPadEffect;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LillyPadParticle extends ParticleBase {

    public LillyPadParticle (Player p) {
        super(p, new LillyPadEffect());
    }

    @Override
    public void onRender(Player p, ParticleEquation.EquationResult result) {

        Location base = p.getLocation();

        if(PlayerMovementTracker.isMoving(p)) {
            p.getWorld().spawnParticle(Particle.FLAME, base, 1, 0.2f, 0.1, 0.2f, 0);
            return;
        }

        for (Vector v : result.points) {

            Location loc = base.clone().add(v);

            p.getWorld().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
        }
    }
}