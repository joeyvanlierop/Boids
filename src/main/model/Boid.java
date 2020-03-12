package model;

import java.io.Serializable;

// Represents an boid having:
//  - A position vector
//  - A velocity vector
//  - A acceleration vector
//  - A mass
public class Boid implements Serializable {
    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;
    protected double mass;

    protected double maxVelocity;

    public static class BoidBuilder {
        private Vector position = new Vector(0, 0);
        private Vector velocity = new Vector(0, 0);
        private Vector acceleration = new Vector(0, 0);
        private double mass = 1;
        private double maxVelocity = 5;
        private double maxAcceleration = 0.005;

        public BoidBuilder setPosition(Vector position) {
            this.position = position;
            return this;
        }

        public BoidBuilder setVelocity(Vector velocity) {
            this.velocity = velocity;
            return this;
        }

        public BoidBuilder setAcceleration(Vector acceleration) {
            this.acceleration = acceleration;
            return this;
        }

        public BoidBuilder setMass(double mass) {
            this.mass = mass;
            return this;
        }

        public BoidBuilder setMaxVelocity(double maxVelocity) {
            this.maxVelocity = maxVelocity;
            return this;
        }

        public Boid build() {
            return new Boid(position, velocity, acceleration, mass, maxVelocity);
        }
    }

    private Boid(Vector position, Vector velocity, Vector acceleration,
                double mass, double maxVelocity) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
        this.maxVelocity = maxVelocity;
    }

    /**
     * EFFECTS: increases the velocity by the acceleration
     * increases the position by the velocity
     * MODIFIES: this
     */
    public void move() {
        velocity.add(acceleration);
        velocity.clamp(maxVelocity, 0.5);
        position.add(velocity);
        acceleration.mult(0);
    }

    public void addForce(Vector force) {
        acceleration.add(force.div(getMass()));
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

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
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
                && b.getMass() == getMass()
                && b.getMaxVelocity() == getMaxVelocity();
    }
}
