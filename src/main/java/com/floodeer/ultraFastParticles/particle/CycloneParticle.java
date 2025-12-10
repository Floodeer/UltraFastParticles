package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.particle.types.CycloneEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CycloneParticle extends ParticleBase {

    public CycloneParticle(Player p) {
        super(p, new CycloneEffect());
    }

    @Override
    public void onRender(Player p, ParticleEquation.EquationResult result) {

        Location base = p.getLocation();

        for (Vector v : result.points) {
            Location loc = base.clone().add(v);
            loc.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0, 0, 0, 0);
        }
    }
}