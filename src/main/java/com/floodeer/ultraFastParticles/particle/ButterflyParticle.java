package com.floodeer.ultraFastParticles.particle;

import com.floodeer.ultraFastParticles.PlayerMovementTracker;
import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import com.floodeer.ultraFastParticles.particle.types.ButterflyEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ButterflyParticle extends ParticleBase {

    public ButterflyParticle(Player p) {
        super(p, new ButterflyEffect());
    }

    /*
     *  Example of the Equation with tick delay and modifying the results in main thread due thread-safety to get player's vector.
     */
    @Override
    public void onRender(Player p, ParticleEquation.EquationResult result) {

        if (result == null)
            return;

        Location base = p.getLocation();

        if (PlayerMovementTracker.isMoving(p)) {
            p.getWorld().spawnParticle(Particle.FLAME, base, 1, 0.2f, 0.1, 0.2f, 0);
            return;
        }

        Location loc = p.getEyeLocation().clone().subtract(0.0D, 0.3D, 0.0D);
        loc.setPitch(0.0F);
        loc.setYaw(p.getEyeLocation().getYaw());

        Vector backward = loc.getDirection().normalize().multiply(-0.3D);
        loc.add(backward);

        Location temp = loc.clone();

        float pitch = (loc.getPitch() + 90.0F) * 0.017F;
        float yaw = -loc.getYaw() * 0.017F;

        for (Vector v : result.points) {

            VelocityUtils.rotateAroundX(v, pitch);
            VelocityUtils.rotateAroundY(v, yaw);

            temp.set(loc.getX() + v.getX(), loc.getY() + v.getY(), loc.getZ() + v.getZ());

            p.getWorld().spawnParticle(Particle.FLAME, temp, 1, 0, 0, 0, 0);
        }
    }
}