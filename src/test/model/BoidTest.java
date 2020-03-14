package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoidTest {
    Boid boid;

    @BeforeEach
    void runBefore() {
        boid = new Boid.BoidBuilder()
                .setPosition(new Vector(0, 0))
                .setVelocity(new Vector(0, 0))
                .setAcceleration(new Vector(0, 0))
                .setMass(1)
                .setMaxVelocity(0)
                .setMaxVelocity(1)
                .build();
    }

    @Test
    void testConstructor() {
        assertEquals(new Vector(0, 0), boid.getPosition());
        assertEquals(new Vector(0, 0), boid.getVelocity());
        assertEquals(new Vector(0, 0), boid.getAcceleration());
        assertEquals(1, boid.getMass());
        assertEquals(1, boid.getMinVelocity());
        assertEquals(1, boid.getMaxVelocity());
    }

    @Test
    void testEquals() {
        assertNotEquals(boid, null);
        assertNotEquals(boid, "Boid");

        Boid other = new Boid.BoidBuilder()
                .setPosition(new Vector(1, 1))
                .setVelocity(new Vector(1, 1))
                .setAcceleration(new Vector(1, 1))
                .setMass(2)
                .setMinVelocity(1)
                .setMaxVelocity(2)
                .build();
        assertNotEquals(boid, other);

        other.setPosition(new Vector(0, 0));
        assertNotEquals(boid, other);

        other.setVelocity(new Vector(0, 0));
        assertNotEquals(boid, other);

        other.setAcceleration(new Vector(0, 0));
        assertNotEquals(boid, other);

        other.setMass(1);
        assertNotEquals(boid, other);

        other.setMinVelocity(0);
        assertNotEquals(boid, other);

        other.setMaxVelocity(1);
        assertEquals(boid, other);
    }

    @Test
    void testMove() {
        boid.setMaxVelocity(10);

        testMoveHelper(new Vector(0, 0), new Vector(0, 0), new Vector(0, 0));
        testMoveHelper(new Vector(0, 0), new Vector(1, 0), new Vector(0, 0));
        testMoveHelper(new Vector(0, 0), new Vector(-1, -1), new Vector(1, 1));
    }

    void testMoveHelper(Vector position, Vector velocity, Vector acceleration) {
        Vector endVelocity = velocity.add(acceleration);
        Vector endPosition = position.add(endVelocity);

        boid.setPosition(position);
        boid.setVelocity(velocity);
        boid.setAcceleration(acceleration);
        boid.move();
        assertEquals(endPosition, boid.getPosition());
        assertEquals(endVelocity, boid.getVelocity());
        assertEquals(new Vector(0, 0), boid.getAcceleration());
    }

    @Test
    void testToString() {
        Vector position = new Vector(0, 0);
        Vector velocity = new Vector(0, 0);
        Vector acceleration = new Vector(0, 0);
        double mass = 1;

        String positionString = position.toString();
        String velocityString = velocity.toString();
        String accelerationString = acceleration.toString();
        assertEquals(String.format("Boid[position=%s, velocity=%s, acceleration=%s, mass=%.2f]",
                positionString, velocityString, accelerationString, mass), boid.toString());
    }
}
