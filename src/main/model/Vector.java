package model;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Vector a, Vector b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public static double angle(Vector a, Vector b) {
        return Math.acos(dot(a, b) / (magnitude(a) * magnitude(b)));
    }

    public static double dot(Vector a, Vector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public static double magnitude(Vector a) {
        return Math.sqrt(dot(a, a));
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public void add(Vector v) {
        setX(getX() + v.getX());
        setY(getY() + v.getY());
    }

    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public void sub(Vector v) {
        setX(getX() - v.getX());
        setY(getY() - v.getY());
    }

    public static Vector mult(Vector a, double scale) {
        return new Vector(a.getX() * scale, a.getY() * scale);
    }

    public void mult(double scale) {
        setX(getX() * scale);
        setY(getY() * scale);
    }

    public static Vector norm(Vector a) {
        return mult(a, 1 / magnitude(a));
    }

    public void norm() {
        setX(getX() / Vector.magnitude(this));
        setY(getY() / Vector.magnitude(this));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Vector[X=%.2f, Y=%.2f]", x, y);
    }
}
