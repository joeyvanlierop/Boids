package model;

import model.behaviour.Behaviour;
import model.behaviour.rules.Rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// Represents a world having:
//  - A width
//  - A height
//  - A standard view angle for all boids
//  - A list of boids
//  - A list of behaviours
public class World implements Serializable {
    private int width;
    private int height;
    private double viewAngle;
    private Behaviour behaviour;
    private ArrayList<Boid> boids;

    public static class WorldBuilder {
        private int width;
        private int height;
        private double viewAngle = 360;
        private Behaviour behaviour = new Behaviour();

        public WorldBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public WorldBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public WorldBuilder setViewAngle(double viewAngle) {
            this.viewAngle = viewAngle;
            return this;
        }

        public WorldBuilder addRule(Rule rule) {
            behaviour.addRule(rule);
            return this;
        }

        public World build() {
            return new World(width, height, viewAngle, behaviour);
        }
    }

    private World(int width, int height, double viewAngle, Behaviour behaviour) {
        this.width = width;
        this.height = height;
        this.viewAngle = viewAngle;
        this.behaviour = behaviour;
        this.boids = new ArrayList<>();
    }

    /**
     * EFFECTS: moves each boid in the world
     */
    public void update() {
        for (Boid boid : boids) {
            behaviour.update(boid, this);
            wrapBoid(boid);
        }
    }

    /**
     * EFFECTS: wraps each boid around the edges of the world
     */
    public void wrapBoid(Boid boid) {
        if (boid.getPosition().getX() > getWidth()) {
            boid.getPosition().setX(0);
        } else if (boid.getPosition().getX() < 0) {
            boid.getPosition().setX(getWidth());
        }

        if (boid.getPosition().getY() > getHeight()) {
            boid.getPosition().setY(0);
        } else if (boid.getPosition().getY() < 0) {
            boid.getPosition().setY(getHeight());
        }
    }

    /**
     * EFFECTS: adds a boid to the world and return
     * - returns the added boid if it is within the world's bounds
     * MODIFIES: this
     */
    public Boid addBoid(Boid boid) {
        if (boid.getPosition().getX() >= 0 && boid.getPosition().getX() <= getWidth()
                && boid.getPosition().getY() >= 0 && boid.getPosition().getY() <= getHeight()) {
            boids.add(boid);
            return boid;
        } else {
            return null;
        }
    }

    /**
     * EFFECTS: adds a boid to the world with a random position and velocity within the bounds
     * - returns the added boid
     * MODIFIES: this
     */
    public Boid addRandomBoid() {
        Random random = new Random();
        double positionX = random.nextDouble() * width;
        double positionY = random.nextDouble() * height;

        Vector position = new Vector(positionX, positionY);

        return addRandomBoid(position);
    }

    /**
     * EFFECTS: adds a boid to the world with a random position within the bounds
     * - returns the added boid
     * MODIFIES: this
     */
    public Boid addRandomBoid(Vector position) {
        Random random = new Random();
        double velocityX = random.nextDouble() * 2 - 1;
        double velocityY = random.nextDouble() * 2 - 1;

        Vector velocity = new Vector(velocityX, velocityY);
        Boid boid = new Boid.BoidBuilder().setPosition(position).setVelocity(velocity).build();

        return addBoid(boid);
    }

    /**
     * EFFECTS: adds the given amount of boids to the world with a random position within the bounds
     * MODIFIES: this
     */
    public void addRandomBoids(int count) {
        for (int i = 0; i < count; i++) {
            addRandomBoid();
        }
    }

    /**
     * EFFECTS: returns a list of the boids near the given boid
     */
    public ArrayList<Boid> getNearbyBoids(Boid boid, double radius) {
        ArrayList<Boid> nearbyBoids = new ArrayList<>();

        for (Boid other : boids) {
            if (other != boid) {
                Vector difference = Vector.sub(other.getPosition(), boid.getPosition());
                double distance = difference.magnitude();
                if (distance <= radius) {
                    if (distance == 0 || Vector.angleBetween(boid.getVelocity(), difference) <= getViewAngle()) {
                        nearbyBoids.add(other);
                    }
                }
            }
        }

        return nearbyBoids;
    }

    /**
     * EFFECTS: returns the number of boids in the world
     */
    public int boidCount() {
        return boids.size();
    }

    public ArrayList<Boid> getBoids() {
        return boids;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getViewAngle() {
        return viewAngle;
    }

    public void setViewAngle(double viewAngle) {
        this.viewAngle = viewAngle;
    }
}
