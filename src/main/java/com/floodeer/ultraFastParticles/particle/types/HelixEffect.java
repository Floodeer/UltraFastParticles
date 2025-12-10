package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class HelixEffect implements ParticleEquation {

    private final float b = 0.04F;
    private final float a = 0.0F;
    private final float radius = 1.9F;
    private final double angle = 0.2617993877991494D;
    private final int particles = 20;

    @Override
    public EquationResult calculate(long step) {

        EquationResult result = new EquationResult();
        result.points = new ArrayList<>();

        double rotation = step * Math.PI / 70.0;

        for (int t = 0; t < particles; t++) {

            double x = (((a + b) * t) * Math.cos(((a + b) * t) * angle / b) - a) * radius;
            double y = t * -0.14D + 2.7;
            double z = (((a + b) * t) * Math.sin(((a + b) * t) * angle / b) - a) * radius;

            Vector v1 = new Vector(x, y, z);
            VelocityUtils.rotateAroundY(v1, rotation);
            result.points.add(v1);

            Vector v2 = new Vector(-x, y, -z);
            VelocityUtils.rotateAroundY(v2, rotation);
            result.points.add(v2);
        }

        return result;
    }
}
