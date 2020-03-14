package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;

import java.util.Random;

// Boid noise rule
public class Noise extends Rule {
    protected Random random;

    public Noise(boolean enabled, double weight) {
        super(enabled, weight);
        this.random = new Random();
    }

    // EFFECTS: returns a vector a randomly scaled vector perpendicular to the given boids current velocity
    @Override
    public Vector update(Boid boid, World world) {
        double scale = random.nextDouble() * 2 - 1;

        Vector noise = new Vector(boid.getVelocity().getY() * scale, boid.getVelocity().getX() * -scale);

        noise.norm();
        noise.mult(boid.getVelocity().magnitude());
        noise.sub(boid.getVelocity());
        noise.mult(weight);

        return noise;
    }
}
