package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.particle.types.CycloneEffect;
import com.floodeer.ultraFastParticles.particle.types.HelixEffect;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HelixParticle extends ParticleBase {

    public HelixParticle(Player p) {
        super(p, new HelixEffect());
    }

    @Override
    public void onRender(Player p, ParticleEquation.EquationResult result) {

        Location base = p.getLocation();

        for (Vector v : result.points) {

            Location loc = base.clone().add(v);

            p.getWorld().spawnParticle(Particle.DUST, loc, 1, new Particle.DustOptions(Color.RED, 1.0f));
        }
    }
}