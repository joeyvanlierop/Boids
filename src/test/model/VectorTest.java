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
        assertNotEquals(iVector, oneVector);
        assertNotEquals(jVector, oneVector);
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
    void testAngleBetween() {
        assertEquals(0, Vector.angleBetween(iVector, iVector));
        assertEquals(90, Vector.angleBetween(iVector, jVector), 0.01);
        assertEquals(90, Vector.angleBetween(jVector, iVector), 0.01);
        assertEquals(180, Vector.angleBetween(oneVector, Vector.mult(oneVector, -1)), 0.01);
        assertEquals(135, Vector.angleBetween(oneVector, Vector.mult(iVector, -1)), 0.01);
    }

    @Test
    void testAngle() {
        Vector testVector1 = new Vector(1, 0);
        Vector testVector2 = new Vector(0, 1);

        assertEquals(0, testVector1.angle());
        assertEquals(Math.PI / 2, testVector2.angle());
    }

    @Test
    void testAdd() {
        Vector testVector = new Vector(0, 0);

        assertEquals(zeroVector, testVector.add(zeroVector));
        assertEquals(iVector, testVector.add(iVector));
        assertEquals(oneVector, testVector.add(jVector));
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

        assertEquals(oneVector, testVector.sub(zeroVector));
        assertEquals(jVector, testVector.sub(iVector));
        assertEquals(zeroVector, testVector.sub(jVector));
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

        assertEquals(new Vector(2, 2), testVector.mult(2));
        assertEquals(testVector, testVector.mult(1));
        assertEquals(new Vector(0, 0), testVector.mult(0));
    }

    @Test
    void testMultStatic() {
        assertEquals(zeroVector, Vector.mult(zeroVector, 2));
        assertEquals(new Vector(0, 2), Vector.mult(jVector, 2));
        assertEquals(new Vector(3, 0), Vector.mult(iVector,3));
        assertEquals(new Vector(4, 4), Vector.mult(oneVector, 4));
    }

    @Test
    void testDiv() {
        Vector testVector = new Vector(2, 2);

        assertEquals(oneVector, testVector.div(2));
        assertEquals(testVector, testVector.div(1));
    }

    @Test
    void testDivStatic() {
        assertEquals(jVector, Vector.div(new Vector(0, 2), 2));
        assertEquals(new Vector(0, 2), Vector.div(new Vector(0, 2), 1));
    }

    @Test
    void testNorm() {
        Vector testVector = new Vector(1, 1);

        assertEquals(1, Vector.magnitude(testVector.norm()), 0.01);
        assertEquals(new Vector(1 / Math.sqrt(2), 1 / Math.sqrt(2)), testVector);
    }

    @Test
    void testNormStatic() {
        assertEquals(jVector, Vector.norm(jVector));
        assertEquals(iVector, Vector.norm(iVector));
        assertEquals(new Vector(1 / Math.sqrt(2), 1 / Math.sqrt(2)), Vector.norm(oneVector));
    }

    @Test
    void testClampMax() {
        assertEquals(jVector, jVector.clamp(1));
        assertEquals(jVector, new Vector(0, 2).clamp(1));
        assertEquals(iVector, new Vector(2, 0).clamp(1));
    }

    @Test
    void testClampMaxMin() {
        assertEquals(jVector, jVector.clamp(1, 0));
        assertEquals(jVector, new Vector(0, 2).clamp(1, 0));
        assertEquals(new Vector(2, 0), new Vector(1, 0).clamp(2, 2));
    }
}