package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldTest {
    World world;

    @BeforeEach
    void runBefore() {
        world = new World(10, 10);
    }

    @Test
    void testConstructor() {
        World world1 = new World(5, 5);
        World world2 = new World(5, 5, 5);

        assertEquals(0, world1.boidCount());
        assertEquals(5, world2.boidCount());
    }

    @Test
    void testAddRandomBoid() {
        Boid boid = world.addRandomBoid();

        assertEquals(1, world.boidCount());
        assertTrue(world.getBoidList().contains(boid));
        assertTrue(boidInBounds(world, boid));
    }

    @Test
    void testTick() {
        Boid boid1 = new Boid(new Vector(0, 0), new Vector(10, 10));
        Boid boid2 = new Boid(new Vector(1, 1), new Vector(1, 2));
        world.addBoid(boid1);
        world.addBoid(boid2);
        world.tick();

        assertEquals(new Vector(10, 10), world.getBoidList().get(0).getPosition());
        assertEquals(new Vector(2, 3), world.getBoidList().get(1).getPosition());
    }

    @Test
    void testAddBoid() {
        Boid boid1 = new Boid(new Vector(0, 0));
        Boid boid2 = new Boid(new Vector(1, 1));
        world.addBoid(boid1);
        world.addBoid(boid2);

        assertTrue(world.getBoidList().contains(boid1));
        assertTrue(world.getBoidList().contains(boid2));
    }

    @Test
    void testAddRandomBoids() {
        final int BOID_COUNT = 200;

        for(int i = 0; i < BOID_COUNT; i++) {
            Boid boid = world.addRandomBoid();
            assertEquals(i + 1, world.boidCount());
            assertTrue(world.getBoidList().contains(boid));
            assertTrue(boidInBounds(world, boid));
        }

        assertEquals(BOID_COUNT, world.boidCount());
    }

    private boolean boidInBounds(World w, Boid b) {
        return b.getPosition().getX() >= 0 && b.getPosition().getX() <= world.getWidth() &&
                b.getPosition().getY() >= 0 && b.getPosition().getY() <= world.getHeight();
    }
}
