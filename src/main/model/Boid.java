package model;

public class Boid extends Body {
    public Boid(Vector position) {
        super(position);
    }

    public Boid(Vector position, Vector velocity) {
        super(position, velocity);
    }

    public Boid(Vector position, Vector velocity, Vector acceleration) {
        super(position, velocity, acceleration);
    }

    public Boid(Vector position, Vector velocity, Vector acceleration, double mass) {
        super(position, velocity, acceleration, mass);
    }
}
