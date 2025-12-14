package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class AuroraEffect implements ParticleEquation {

    private static double verticalOffset = 0.1D;

    @Override
    public EquationResult calculate(long step) {

        EquationResult result = new EquationResult();
        result.points = new ArrayList<>(4);

        double rotationAngle = step * Math.PI / 18.0D;

        for (int i = 1; i < 3; i++) {

            double sideOffset = (i % 2 == 0) ? -1.5D : 1.5D;

            Vector spiralVector = new Vector(Math.cos(rotationAngle) * sideOffset, verticalOffset, Math.sin(rotationAngle) * sideOffset);
            
            result.points.add(spiralVector.clone());
            
            verticalOffset += 0.1D;
            if (verticalOffset >= 1.0D) {
                verticalOffset = 0.0D;
            }
        }
        return result;
    }
}
