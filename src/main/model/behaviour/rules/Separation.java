package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;

import java.util.ArrayList;

// Boid separation rule
public class Separation extends Rule {
    protected double radius;

    public Separation(boolean enabled, double weight, double radius) {
        super(enabled, weight);
        this.radius = radius;
    }

    // EFFECTS: returns a vector with the steering force away from nearby boids
    @Override
    public Vector update(Boid boid, World world) {
        ArrayList<Boid> nearbyBoids = world.getNearbyBoids(boid, radius);
        Vector separation = new Vector(0, 0);

        if (nearbyBoids.size() == 0) {
            return separation;
        }

        for (Boid nearbyBoid : nearbyBoids) {
            separation.add(Vector.sub(boid.getPosition(), nearbyBoid.getPosition()));
        }

        separation.div(nearbyBoids.size());
        separation.sub(boid.getVelocity());
        separation.norm();
        separation.mult(weight);
        return separation;
    }
}
