package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoidTest {
    @Test
    void testConstructor() {
        Boid boid1 = new Boid(new Vector(0, 0));
        Boid boid2 = new Boid(new Vector(1, 1), new Vector(2, 2));
        Boid boid3 = new Boid(new Vector(3, 3), new Vector(4, 4), new Vector(5, 5));
        Boid boid4 = new Boid(new Vector(6, 6), new Vector(7, 7), new Vector(8, 8), 9);

        assertEquals(new Vector(0, 0), boid1.getPosition());
        assertEquals(new Vector(0, 0), boid1.getVelocity());
        assertEquals(new Vector(0, 0), boid1.getAcceleration());
        assertEquals(1, boid1.getMass());
        boid1.setMass(10);
        assertEquals(10, boid1.getMass());
        boid1.setPosition(new Vector(5, 5));
        assertEquals(new Vector(5, 5), boid1.getPosition());

        assertEquals(new Vector(1, 1), boid2.getPosition());
        assertEquals(new Vector(2, 2), boid2.getVelocity());
        assertEquals(new Vector(0, 0), boid2.getAcceleration());
        assertEquals(1, boid2.getMass());

        assertEquals(new Vector(3, 3), boid3.getPosition());
        assertEquals(new Vector(4, 4), boid3.getVelocity());
        assertEquals(new Vector(5, 5), boid3.getAcceleration());
        assertEquals(1, boid3.getMass());

        assertEquals(new Vector(6, 6), boid4.getPosition());
        assertEquals(new Vector(7, 7), boid4.getVelocity());
        assertEquals(new Vector(8, 8), boid4.getAcceleration());
        assertEquals(9, boid4.getMass());
    }

    @Test
    void testEquals() {
        Boid boid1 = new Boid(new Vector(0, 0));
        Boid boid2 = new Boid(new Vector(0, 0), new Vector(1, 1));
        Boid boid3 = new Boid(new Vector(0, 0), new Vector(1, 1), new Vector(2, 2));
        Boid boid4 = new Boid(new Vector(0, 0), new Vector(1, 1), new Vector(2, 2), 3);
        assertNotEquals(boid1, null);
        assertNotEquals(boid1, "Boid");
        assertNotEquals(boid1, boid2);
        assertNotEquals(boid2, boid3);
        assertNotEquals(boid3, boid4);
        assertEquals(boid1, new Boid(new Vector(0, 0)));
        assertEquals(boid4, new Boid(new Vector(0, 0), new Vector(1, 1), new Vector(2, 2), 3));
    }

    @Test
    void testMove() {
        Boid boid = new Boid(new Vector(0, 0));

        boid.move();
        assertEquals(new Vector(0, 0), boid.getPosition());

        boid.setVelocity(new Vector(1, 2));
        boid.move();
        assertEquals(new Vector(1, 2), boid.getPosition());

        boid.setAcceleration(new Vector(1, 1));
        boid.move();
        assertEquals(new Vector(2, 3), boid.getVelocity());
        assertEquals(new Vector(3, 5), boid.getPosition());
    }
    
    @Test
    void testToString() {
        Vector position = new Vector(0, 0);
        Vector velocity = new Vector(0, 0);
        Vector acceleration = new Vector(0, 0);
        double mass = 1;
        Boid boid = new Boid(position, velocity, acceleration, mass);

        String positionString = position.toString();
        String velocityString = velocity.toString();
        String accelerationString = acceleration.toString();
        assertEquals(String.format("Boid[position=%s, velocity=%s, acceleration=%s, mass=%.2f]",
                positionString, velocityString, accelerationString, mass), boid.toString());
    }
}
