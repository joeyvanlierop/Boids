package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// Represents a world having:
//  - A width
//  - A height
//  - A list of boids
public class World implements Serializable {
    private int width;
    private int height;
    private ArrayList<Boid> boidList;

    /**
     * REQUIRES: width is greater than zero
     *           height is greater than zero
     * EFFECTS: constructs a world with: the given width and height
     *          no boids are automatically generated
     */
    public World(int width, int height) {
        this(width, height, 0);
    }

    /**
     * REQUIRES: width is greater than zero
     *           height is greater than zero
     * EFFECTS: constructs a world with: the given width and height and
     *          generates the given amount of boids randomly placed within the bounds
     */
    public World(int width, int height, int boidCount) {
        this.width = width;
        this.height = height;
        this.boidList = new ArrayList<>();
        this.addRandomBoids(boidCount);
    }

    /**
     * EFFECTS: moves each boid in the world
     */
    public void tick() {
        for (Boid boid : boidList) {
            boid.move();
        }
    }

    /**
     * EFFECTS: adds a boid to the world and return
     *          - returns the added boid if it is within the world's bounds
     * MODIFIES: this
     */
    public Boid addBoid(Boid boid) {
        if (boid.getPosition().getX() >= 0 && boid.getPosition().getX() <= getWidth()
                && boid.getPosition().getY() >= 0 && boid.getPosition().getY() <= getHeight()) {
            boidList.add(boid);
            return boid;
        } else {
            return null;
        }
    }

    /**
     * EFFECTS: adds a boid to the world with a random position within the bounds
     *          - returns the added boid
     * MODIFIES: this
     */
    public Boid addRandomBoid() {
        Random random = new Random();
        double positionX = random.nextDouble() * width;
        double positionY = random.nextDouble() * height;
        double velocityX = random.nextDouble();
        double velocityY = random.nextDouble();

        Vector position = new Vector(positionX, positionY);
        Vector velocity = new Vector(velocityX, velocityY);
        Boid boid = new Boid(position, velocity);

        boidList.add(boid);
        return boid;
    }

    /**
     * EFFECTS: adds thr given amount of boids to the world with a random position within the bounds
     * MODIFIES: this
     */
    public void addRandomBoids(int count) {
        for (int i = 0; i < count; i++) {
            addRandomBoid();
        }
    }

    public int boidCount() {
        return boidList.size();
    }

    public ArrayList<Boid> getBoidList() {
        return boidList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
