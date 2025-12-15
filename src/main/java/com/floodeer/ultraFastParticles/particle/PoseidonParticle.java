package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.PlayerMovementTracker;
import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.particle.types.LillyPadEffect;
import com.floodeer.ultraFastParticles.particle.types.PoseidonEffect;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PoseidonParticle extends ParticleBase {

    public PoseidonParticle(Player p) {
        super(p, new PoseidonEffect());
    }

    @Override
    public void onRender(Player p, ParticleEquation.EquationResult result) {

        Location base = p.getLocation();

        if(PlayerMovementTracker.isMoving(p)) {
            p.getWorld().spawnParticle(Particle.DUST, base, 1, new Particle.DustOptions(Color.BLUE, 1.0f));
            return;
        }

        for (Vector v : result.points) {

            Location loc = base.clone().add(v);

            p.getWorld().spawnParticle(Particle.DUST, loc, 1, new Particle.DustOptions(Color.BLUE, 1.0f));
        }
    }
}