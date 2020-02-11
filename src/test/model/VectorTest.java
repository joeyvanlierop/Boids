package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VectorTest {
    Vector zeroVector;
    Vector oneVector;
    Vector iVector;
    Vector jVector;

    @BeforeEach
    void runBefore() {
        zeroVector = new Vector(0, 0);
        oneVector = new Vector(1, 1);
        iVector = new Vector(1, 0);
        jVector = new Vector(0, 1);
    }

    @Test
    void testEquals() {
        assertNotEquals(oneVector, null);
        assertNotEquals(oneVector, "oneVector");
        assertNotEquals(zeroVector, oneVector);
        assertNotEquals(iVector, jVector);
        assertNotEquals(jVector, iVector);
        assertEquals(iVector, iVector);
        assertEquals(new Vector(1, 1), oneVector);
    }

    @Test
    void testToString() {
        assertEquals("Vector[X=0.00, Y=0.00]", zeroVector.toString());
        assertEquals("Vector[X=1.00, Y=0.00]", iVector.toString());
        assertEquals("Vector[X=0.00, Y=1.00]", jVector.toString());
        assertEquals("Vector[X=1.00, Y=1.00]", oneVector.toString());
    }

    @Test
    void testDistance() {
        assertEquals(0, Vector.distance(zeroVector, zeroVector));
        assertEquals(Math.sqrt(2), Vector.distance(iVector, jVector));
        assertEquals(Math.sqrt(2), Vector.distance(zeroVector, oneVector));
    }

    @Test
    void testAngle() {
        assertEquals(0, Vector.angle(iVector, iVector));
        assertEquals(Math.PI / 2, Vector.angle(iVector, jVector));
    }

    @Test
    void testDot() {
        assertEquals(0, Vector.dot(zeroVector, iVector));
        assertEquals(0, Vector.dot(iVector, jVector));
        assertEquals(1, Vector.dot(iVector, iVector));
        assertEquals(2, Vector.dot(oneVector, oneVector));
    }

    @Test
    void testMagnitude() {
        assertEquals(0, Vector.magnitude(zeroVector));
        assertEquals(1, Vector.magnitude(iVector));
        assertEquals(1, Vector.magnitude(jVector));
        assertEquals(Math.sqrt(2), Vector.magnitude(oneVector));
    }

    @Test
    void testAdd() {
        Vector testVector = new Vector(0, 0);

        testVector.add(zeroVector);
        assertEquals(zeroVector, testVector);
        testVector.add(iVector);
        assertEquals(iVector, testVector);
        testVector.add(jVector);
        assertEquals(oneVector, testVector);
    }

    @Test
    void testAddStatic() {
        assertEquals(zeroVector, Vector.add(zeroVector, zeroVector));
        assertEquals(jVector, Vector.add(zeroVector, jVector));
        assertEquals(iVector, Vector.add(zeroVector, iVector));
        assertEquals(oneVector, Vector.add(iVector, jVector));
    }

    @Test
    void testSub() {
        Vector testVector = new Vector(1, 1);

        testVector.sub(zeroVector);
        assertEquals(oneVector, testVector);
        testVector.sub(iVector);
        assertEquals(jVector, testVector);
        testVector.sub(jVector);
        assertEquals(zeroVector, testVector);
    }

    @Test
    void testSubStatic() {
        assertEquals(zeroVector, Vector.sub(zeroVector, zeroVector));
        assertEquals(jVector, Vector.sub(jVector, zeroVector));
        assertEquals(iVector, Vector.sub(iVector,zeroVector));
        assertEquals(iVector, Vector.sub(oneVector, jVector));
        assertEquals(jVector, Vector.sub(oneVector, iVector));
    }

    @Test
    void testMult() {
        Vector testVector = new Vector(1, 1);

        testVector.mult(2);
        assertEquals(new Vector(2, 2), testVector);
        testVector.mult(1);
        assertEquals(testVector, testVector);
        testVector.mult(0);
        assertEquals(new Vector(0, 0), testVector);
    }

    @Test
    void testMultStatic() {
        assertEquals(zeroVector, Vector.mult(zeroVector, 2));
        assertEquals(new Vector(0, 2), Vector.mult(jVector, 2));
        assertEquals(new Vector(3, 0), Vector.mult(iVector,3));
        assertEquals(new Vector(4, 4), Vector.mult(oneVector, 4));
    }

    @Test
    void testNorm() {
        Vector testVector = new Vector(1, 1);

        testVector.norm();
        assertEquals(1, Vector.magnitude(testVector), 0.01);
        assertEquals(new Vector(1 / Math.sqrt(2), 1 / Math.sqrt(2)), testVector);
    }

    @Test
    void testNormStatic() {
        assertEquals(jVector, Vector.norm(jVector));
        assertEquals(iVector, Vector.norm(iVector));
        assertEquals(new Vector(1 / Math.sqrt(2), 1 / Math.sqrt(2)), Vector.norm(oneVector));
    }
}