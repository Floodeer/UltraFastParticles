package com.floodeer.ultraFastParticles.math;

import org.bukkit.util.Vector;

import java.util.List;

public interface ParticleEquation {

    EquationResult calculate(long step);

    class EquationResult {
        public List<Vector> points;
    }
}