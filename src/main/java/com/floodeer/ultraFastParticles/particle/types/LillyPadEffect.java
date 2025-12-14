package com.floodeer.ultraFastParticles.particle.types;

import com.floodeer.ultraFastParticles.math.ParticleEquation;
import com.floodeer.ultraFastParticles.math.VelocityUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class LillyPadEffect implements ParticleEquation {

    private int particles = 150;
    private int particlesPerIteration = 12;
    private float size = 1.0F;
    private float xFactor = 1.0F;
    private float yFactor = 0.6F;
    private float zFactor = 1.0F;
    private float yOffset = 0.6F;
    private double xRotation = 0.0D;
    private double yRotation = 0.0D;
    private double zRotation = 0.0D;
    private int lillyStep;

    private static final float PI = 3.141592653589793F;
    private static final float E = 2.718281828459045F;

    @Override
    public EquationResult calculate(long step) {

        EquationResult result = new EquationResult();
        int particles = 14;
        result.points = new ArrayList<>(particles);
        Vector vector = new Vector();

        for(int i = 0; i < this.particlesPerIteration; i++) {
            this.lillyStep += 1;
            if(this.lillyStep > 400)
                this.lillyStep = 0;

            float angle = PI / this.particles * this.lillyStep;
            float radiusOffset = (float) (Math.sin(angle * E * this.particlesPerIteration / this.particles) * this.size);
            float height = (radiusOffset * PI * angle);

            vector.setX(this.xFactor * radiusOffset * -Math.cos(height));
            vector.setZ(this.zFactor * radiusOffset * -Math.sin(height));
            vector.setY(this.yFactor + this.yOffset - 1.0F);

            VelocityUtils.rotate(vector, this.xRotation, this.yRotation, this.zRotation);

            result.points.add(vector);
        }

        return result;
    }
}
