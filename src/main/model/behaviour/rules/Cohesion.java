package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;

import java.util.ArrayList;

// Boid cohesion rule
public class Cohesion extends Rule {
    protected double radius;

    public Cohesion(boolean enabled, double weight, double radius) {
        super(enabled, weight);
        this.radius = radius;
    }

    // EFFECTS: returns a vector with the steering force towards the average position of nearby boids
    @Override
    public Vector update(Boid boid, World world) {
        ArrayList<Boid> nearbyBoids = world.getNearbyBoids(boid, radius);
        Vector cohesion = new Vector(0, 0);

        if (nearbyBoids.size() == 0) {
            return cohesion;
        }

        for (Boid nearbyBoid : nearbyBoids) {
            cohesion.add(nearbyBoid.getPosition());
        }

        cohesion.div(nearbyBoids.size());
        cohesion.sub(boid.getPosition());
        cohesion.mult(weight);
        return cohesion;
    }
}
