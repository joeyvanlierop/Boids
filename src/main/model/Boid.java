package model;

import java.io.Serializable;

// Represents an boid having:
//  - A position vector
//  - A velocity vector
//  - A acceleration vector
//  - A mass
public class Boid implements Serializable {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    /**
     * EFFECTS: constructs a boid with the given position
     * the velocity and acceleration are set to (0, 0)
     * the mass is set to 1
     */
    public Boid(Vector position) {
        this(position, new Vector(0, 0));
    }

    /**
     * EFFECTS: constructs a boid with the given position and velocity
     * the acceleration is set to (0, 0)
     * the mass is set to 1
     */
    public Boid(Vector position, Vector velocity) {
        this(position, velocity, new Vector(0, 0));
    }

    /**
     * EFFECTS: constructs a boid with the given position, velocity, and acceleration
     * the mass is set to 1
     */
    public Boid(Vector position, Vector velocity, Vector acceleration) {
        this(position, velocity, acceleration, 1);
    }

    /**
     * EFFECTS: constructs a boid with the given position, velocity, acceleration, and mass
     */
    public Boid(Vector position, Vector velocity, Vector acceleration, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    /**
     * EFFECTS: increases the velocity by the acceleration
     * increases the position by the velocity
     * MODIFIES: this
     */
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
        return String.format("Boid[position=%s, velocity=%s, acceleration=%s, mass=%.2f]",
                position, velocity, acceleration, mass);
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
        final Boid b = (Boid) o;
        return b.getPosition().equals(getPosition())
                && b.getVelocity().equals(getVelocity())
                && b.getAcceleration().equals(getAcceleration())
                && b.getMass() == getMass();
    }
}
