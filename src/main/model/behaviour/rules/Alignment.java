package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;

import java.util.ArrayList;

// Boid alignment rule
public class Alignment extends Rule {
    protected double radius;

    public Alignment(boolean enabled, double weight, double radius) {
        super(enabled, weight);
        this.radius = radius;
    }

    // EFFECTS: returns a vector with the steering force towards the average heading of nearby boids
    @Override
    public Vector update(Boid boid, World world) {
        ArrayList<Boid> nearbyBoids = world.getNearbyBoids(boid, radius);
        Vector alignment = new Vector(0, 0);

        if (nearbyBoids.size() == 0) {
            return alignment;
        }

        for (Boid nearbyBoid : nearbyBoids) {
            alignment.add(nearbyBoid.getVelocity());
        }

        alignment.div(nearbyBoids.size());
        alignment.sub(boid.getVelocity());
        alignment.mult(weight);
        return alignment;
    }
}
