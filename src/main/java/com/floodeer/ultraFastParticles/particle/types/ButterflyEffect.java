package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ButterflyEffect implements ParticleEquation {

    @Override
    public EquationResult calculate(long step) {
        if(step % 5 != 0)
            return null;

        EquationResult result = new EquationResult();
        int particles = 14;
        result.points = new ArrayList<>(particles);

        for (double i = -10.0D; i < 6.2D; i += 0.2) {
            double var = Math.sin(i / 12.0D);
            double x = Math.sin(i)* (Math.exp(Math.cos(i)) - 2.0D * Math.cos(4.0D * i) - Math.pow(var, 5.0D)) / 2.0D;
            double z = Math.cos(i)* (Math.exp(Math.cos(i)) - 2.0D * Math.cos(4.0D * i) - Math.pow(var, 5.0D)) / 2.0D;
            Vector v = new Vector(-x, 0.0D, -z);

            result.points.add(v);
        }

        return result;
    }
}