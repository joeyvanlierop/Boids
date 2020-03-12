package model;

import java.io.Serializable;

// Represents a two dimensional vector having:
//  - A x component
//  - A y component
public class Vector implements Serializable {
    private double componentX;
    private double componentY;

    /**
     * EFFECTS: constructs a vector with the given x and y components
     */
    public Vector(double x, double y) {
        componentX = x;
        componentY = y;
    }

    /**
     * EFFECTS: returns the distance between two vectors
     */
    public static double distance(Vector a, Vector b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    /**
     * REQUIRES: a and b must not be zero-vectors
     * EFFECTS: returns the angle between two vectors in radians
     */
    public static double rot(Vector a, Vector b) {
        return Math.acos(dot(a, b) / (magnitude(a) * magnitude(b)));
    }

    /**
     * EFFECTS: returns the rotation of the vector in radian
     */
    public double rot() {
        return Math.atan2(getY(), getX());
    }

    /**
     * EFFECTS: returns the dot product of two vectors
     */
    public static double dot(Vector a, Vector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    /**
     * EFFECTS: returns the length of a vector
     */
    public static double magnitude(Vector a) {
        return Math.sqrt(Vector.dot(a, a));
    }

    /**
     * EFFECTS: returns the length of this vector
     */
    public double magnitude() {
        return Math.sqrt(dot(this, this));
    }

    /**
     * EFFECTS: returns the sum of two vectors
     */
    public static Vector add(Vector a, Vector b) {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    /**
     * EFFECTS: adds a vector to this vector
     * MODIFIES: this
     */
    public Vector add(Vector v) {
        setX(getX() + v.getX());
        setY(getY() + v.getY());
        return this;
    }

    /**
     * EFFECTS: returns the subtraction of two vectors
     */
    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    /**
     * EFFECTS: subtracts a vector from this vector
     * MODIFIES: this
     */
    public Vector sub(Vector v) {
        setX(getX() - v.getX());
        setY(getY() - v.getY());
        return this;
    }

    /**
     * EFFECTS: returns the scalar multiplication of a vector and a scalar
     */
    public static Vector mult(Vector a, double scale) {
        return new Vector(a.getX() * scale, a.getY() * scale);
    }

    /**
     * EFFECTS: multiplies this vector a vector by a scalar
     * MODIFIES: this
     */
    public Vector mult(double scale) {
        setX(getX() * scale);
        setY(getY() * scale);
        return this;
    }

    /**
     * REQUIRES: scale is not 0
     * EFFECTS: returns the scalar division of a vector and a scalar
     */
    public static Vector div(Vector a, double scale) {
        return new Vector(a.getX() / scale, a.getY() / scale);
    }

    /**
     * REQUIRES: scale is not 0
     * EFFECTS: divides this vector a vector by a scalar
     * MODIFIES: this
     */
    public Vector div(double scale) {
        setX(getX() / scale);
        setY(getY() / scale);
        return this;
    }

    /**
     * EFFECTS: returns the normalization of a vector
     */
    public static Vector norm(Vector a) {
        return mult(a, 1 / magnitude(a));
    }

    /**
     * EFFECTS: normalizes this vector
     * MODIFIES: this
     */
    public Vector norm() {
        mult(1 / magnitude());
        return this;
    }

    /**
     * EFFECTS: clamps this vector to the given length
     * MODIFIES: this
     */
    public Vector clamp(double maxLength) {
        double length = magnitude();

        if (length > maxLength) {
            return norm().mult(maxLength);
        }

        return this;
    }

    /**
     * EFFECTS: clamps this vector to the given length
     * MODIFIES: this
     */
    public Vector clamp(double maxLength, double minLength) {
        double length = magnitude();

        if (length > maxLength) {
            return norm().mult(maxLength);
        } else if (length < minLength) {
            return norm().mult(minLength);
        }

        return this;
    }

    public double getX() {
        return componentX;
    }

    public void setX(double x) {
        this.componentX = x;
    }

    public double getY() {
        return componentY;
    }

    public void setY(double y) {
        this.componentY = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o.getClass() != getClass()) {
            return false;
        }

        final Vector v = (Vector) o;
        return v.getX() == getX() && v.getY() == getY();
    }

    @Override
    public String toString() {
        return String.format("Vector[X=%.2f, Y=%.2f]", componentX, componentY);
    }
}
