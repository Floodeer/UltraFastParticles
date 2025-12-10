package com.floodeer.ultraFastParticles.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VelocityUtils {

    public static Vector getRandomVectorLine() {
        int min = -5;
        int max = 5;
        int rz = (int) (Math.random() * (max - min) + min);
        int rx = (int) (Math.random() * (max - min) + min);

        double miny = -5.0D;
        double maxy = -1.0D;
        double ry = Math.random() * (maxy - miny) + miny;

        return new Vector(rx, ry, rz).normalize();
    }

    public static Vector rotateAroundX(Vector vector, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newY = vector.getY() * cos - vector.getZ() * sin;
        double newZ = vector.getY() * sin + vector.getZ() * cos;

        return vector.setY(newY).setZ(newZ);
    }

    public static Vector rotateAroundY(Vector vector, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newX = vector.getX() * cos + vector.getZ() * sin;
        double newZ = vector.getZ() * cos - vector.getX() * sin;

        return vector.setX(newX).setZ(newZ);
    }

    public static Vector rotateAroundZ(Vector vector, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double newX = vector.getX() * cos - vector.getY() * sin;
        double newY = vector.getX() * sin + vector.getY() * cos;

        return vector.setX(newX).setY(newY);
    }

    public static Vector rotate(Vector vector, double x, double y, double z) {
        rotateAroundZ(vector, z);
        rotateAroundY(vector, y);
        rotateAroundX(vector, x);
        return vector;
    }

    public static Vector rotate(Vector v, Location loc) {
        double yaw = (loc.getYaw() / 180.0F) * Math.PI;
        double pitch = (loc.getPitch() / 180.0F) * Math.PI;
        v = VelocityUtils.rotateAroundX(v, pitch);
        v = VelocityUtils.rotateAroundY(v, -yaw);
        return v;
    }

    public static double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }

    public static Vector getBackVector(Location loc) {
        final float newZ = (float) (loc.getZ() + (1 * Math.sin(Math.toRadians(loc.getYaw() + 90))));
        final float newX = (float) (loc.getX() + (1 * Math.cos(Math.toRadians(loc.getYaw() + 90))));
        return new Vector(newX - loc.getX(), 0, newZ - loc.getZ());
    }
}