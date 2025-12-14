package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class PoseidonEffect implements ParticleEquation {

    private double radiusA = 1.0D;
    private double heightA = 0.0D;

    private double radiusB = 1.0D;
    private double heightB = 1.0D;

    private static final double ANGLE_STEP = 0.157D;

    @Override
    public EquationResult calculate(long step) {

        EquationResult result = new EquationResult();
        result.points = new ArrayList<>(4);

        for (int i = 1; i < 3; i++) {

            double phaseOffset = Math.toRadians(180 * i);
            double angle = step * ANGLE_STEP + phaseOffset;

            Vector helixA = new Vector(Math.cos(angle) * radiusA, heightA, Math.sin(angle) * radiusA);

            Vector helixB = new Vector(Math.cos(angle) * radiusB, heightB, Math.sin(angle) * radiusB);

            result.points.add(helixA);
            result.points.add(helixB);
        }

        heightA += 0.05D;
        heightB += 0.05D;

        if (heightA >= 2.5D) {
            heightA = 0.0D;
            radiusA = 1.0D;
        }

        if (heightB >= 2.5D) {
            heightB = 0.0D;
            radiusB = 1.0D;
        }

        if (heightA >= 2.1D) {
            radiusA -= 0.1D;
        }

        if (heightB >= 2.1D) {
            radiusB -= 0.1D;
        }

        return result;
    }
}
