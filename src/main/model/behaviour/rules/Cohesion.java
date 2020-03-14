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
        Vector averagePosition = new Vector(0, 0);
        Vector cohesion = new Vector(0, 0);

        if (nearbyBoids.size() == 0) {
            return averagePosition;
        }

        for (Boid nearbyBoid : nearbyBoids) {
            averagePosition.add(nearbyBoid.getPosition());
        }

        averagePosition.div(nearbyBoids.size());
        averagePosition.sub(boid.getPosition());
        cohesion = Vector.sub(averagePosition, boid.getVelocity());
        cohesion.norm();
        cohesion.mult(weight);
        return cohesion;
    }
}
