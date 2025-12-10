package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class CycloneEffect implements ParticleEquation {

    private double radius = 3;
    private double y = 2;
    private boolean loop = false;

    @Override
    public EquationResult calculate(long step) {

        EquationResult result = new EquationResult();
        int particles = 14;
        result.points = new ArrayList<>(particles);

        double angularVelocityY = Math.PI / 30;
        double yRotation = step * angularVelocityY;

        for (int i = 0; i < particles; i++) {

            double x = Math.cos(i * Math.PI / 4) * radius;
            double z = Math.sin(i * Math.PI / 4) * radius;

            Vector v = new Vector(x, y, z);

            VelocityUtils.rotateAroundY(v, loop ? yRotation : -yRotation);

            result.points.add(v);
        }

        if (!loop) {
            radius -= 0.05;
            y -= 0.05;
            if (radius <= 0.5) loop = true;
        } else {
            radius += 0.05;
            y += 0.05;
            if (radius >= 3) loop = false;
        }

        return result;
    }
}
