package model;

public class Body {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    public Body(Vector position) {
        this(position, new Vector(0, 0));
    }

    public Body(Vector position, Vector velocity) {
        this(position, velocity, new Vector(0, 0));
    }

    public Body(Vector position, Vector velocity, Vector acceleration) {
        this(position, velocity, acceleration, 1);
    }

    public Body(Vector position, Vector velocity, Vector acceleration, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public void move() {
        velocity.add(acceleration);
        position.add(velocity);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return String.format("Body[position=%s, velocity=%s, mass=%.2f]", position, velocity, mass);
    }
}
