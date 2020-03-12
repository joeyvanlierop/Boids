package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;

import java.io.Serializable;

// Represents an abstract behaviour rule having:
//  - An enabled flag
//  - A weight
public abstract class Rule implements Serializable {
    protected boolean enabled;
    protected double weight;

    public Rule(boolean enabled, double weight) {
        this.enabled = enabled;
        this.weight = weight;
    }

    // EFFECTS: updates the given boid in the given world:
    public abstract Vector update(Boid boid, World world);

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
