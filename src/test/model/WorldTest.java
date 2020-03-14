package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    World world;

    @BeforeEach
    void runBefore() {
        world = new World.WorldBuilder().setWidth(10).setHeight(10).build();
    }

    @Test
    void testConstructor() {
        assertEquals(10, world.getWidth());
        assertEquals(10, world.getHeight());
        assertNotNull(world.getBehaviour());
    }

    @Test
    void testChangeSize() {
        world.setWidth(100);
        assertEquals(100, world.getWidth());
        world.setHeight(100);
        assertEquals(100, world.getHeight());
    }

    @Test
    void testAddRandomBoid() {
        Boid boid = world.addRandomBoid();

        assertEquals(1, world.boidCount());
        assertTrue(world.getBoids().contains(boid));
        assertTrue(boidInBounds(world, boid));
    }

    @Test
    void testAddRandomBoids() {
        final int BOID_COUNT = 200;

        world.addRandomBoids(BOID_COUNT);
        assertEquals(BOID_COUNT, world.boidCount());
    }

    @Test
    void testUpdate() {
        Boid boid = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 0)).setVelocity(new Vector(10, 10)).build());
        boid.setMaxVelocity(100);
        world.update();
        assertEquals(new Vector(10, 10), world.getBoids().get(0).getPosition());
    }

    @Test
    void testAddBoid() {
        Boid boid1 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 0)).build());
        Boid boid2 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 1)).build());
        Boid boid3 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(-1, 1)).build());
        Boid boid4 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, -1)).build());
        Boid boid5 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(11, 1)).build());
        Boid body6 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 11)).build());

        assertTrue(world.getBoids().contains(boid1));
        assertTrue(world.getBoids().contains(boid2));
        assertNull(boid3);
        assertNull(boid4);
        assertNull(boid5);
        assertNull(body6);
    }

    @Test
    void testWrapBoid() {
        Boid boid = world.addBoid(new Boid.BoidBuilder().build());

        testWrapBoidHelper(new Vector(-1, 0), new Vector(10, 0));
        testWrapBoidHelper(new Vector(11, 0), new Vector(0, 0));
        testWrapBoidHelper(new Vector(0, -1), new Vector(0, 10));
        testWrapBoidHelper(new Vector(0, 11), new Vector(0, 0));
        testWrapBoidHelper(new Vector(11, 11), new Vector(0, 0));
        testWrapBoidHelper(new Vector(-1, -1), new Vector(10, 10));
    }

    private void testWrapBoidHelper(Vector startPosition, Vector endPosition) {
        Boid boid = world.addBoid(new Boid.BoidBuilder().build());

        boid.setPosition(startPosition);
        world.wrapBoid(boid);
        assertEquals(endPosition, boid.getPosition());
    }

    @Test
    void testGetNearbyBoids() {
        Boid boid1 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 0)).build());
        Boid boid2 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 1)).build());
        Boid boid3 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(5, 0)).build());

        assertEquals(2, world.getNearbyBoids(boid1, 5).size());
        assertEquals(1, world.getNearbyBoids(boid2, 3).size());
        assertEquals(0, world.getNearbyBoids(boid3, 1).size());
    }

    private boolean boidInBounds(World w, Boid b) {
        return b.getPosition().getX() >= 0 && b.getPosition().getX() <= world.getWidth() &&
                b.getPosition().getY() >= 0 && b.getPosition().getY() <= world.getHeight();
    }
}
