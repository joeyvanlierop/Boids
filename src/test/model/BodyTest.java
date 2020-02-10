package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BodyTest {
    @Test
    void testConstructor() {
        Body body1 = new Body(new Vector(0, 0));
        Body body2 = new Body(new Vector(1, 1), new Vector(2, 2));
        Body body3 = new Body(new Vector(3, 3), new Vector(4, 4), new Vector(5, 5));
        Body body4 = new Body(new Vector(6, 6), new Vector(7, 7), new Vector(8, 8), 9);

        assertEquals(new Vector(0, 0), body1.getPosition());
        assertEquals(new Vector(0, 0), body1.getVelocity());
        assertEquals(new Vector(0, 0), body1.getAcceleration());
        assertEquals(1, body1.getMass());

        assertEquals(new Vector(1, 1), body2.getPosition());
        assertEquals(new Vector(2, 2), body2.getVelocity());
        assertEquals(new Vector(0, 0), body2.getAcceleration());
        assertEquals(1, body2.getMass());

        assertEquals(new Vector(3, 3), body3.getPosition());
        assertEquals(new Vector(4, 4), body3.getVelocity());
        assertEquals(new Vector(5, 5), body3.getAcceleration());
        assertEquals(1, body3.getMass());

        assertEquals(new Vector(6, 6), body4.getPosition());
        assertEquals(new Vector(7, 7), body4.getVelocity());
        assertEquals(new Vector(8, 8), body4.getAcceleration());
        assertEquals(9, body4.getMass());
    }

    @Test
    void testMove() {
        Body body = new Body(new Vector(0, 0));

        body.move();
        assertEquals(new Vector(0, 0), body.getPosition());

        body.setVelocity(new Vector(1, 2));
        body.move();
        assertEquals(new Vector(1, 2), body.getPosition());

        body.setAcceleration(new Vector(1, 1));
        body.move();
        assertEquals(new Vector(2, 3), body.getVelocity());
        assertEquals(new Vector(3, 5), body.getPosition());
    }
}
