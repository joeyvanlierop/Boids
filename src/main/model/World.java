package model;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private int width;
    private int height;
    private ArrayList<Boid> boidList;

    public World(int width, int height, int boidCount) {
        this(width, height);
        addRandomBoids(boidCount);
    }

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.boidList = new ArrayList<>();
    }

    public void tick() {
        for (Boid boid : boidList) {
            boid.move();
        }
    }

    public void addRandomBoid() {
        Random random = new Random();
        double positionX = random.nextDouble() * width;
        double positionY = random.nextDouble() * height;
        double velocityX = random.nextDouble();
        double velocityY = random.nextDouble();

        Vector position = new Vector(positionX, positionY);
        Vector velocity = new Vector(velocityX, velocityY);
        Boid boid = new Boid(position, velocity);

        boidList.add(boid);
    }

    public void addRandomBoids(int count) {
        for (int i = 0; i < count; i++) {
            addRandomBoid();
        }
    }

    public ArrayList<Boid> getBoidList() {
        return boidList;
    }
}
