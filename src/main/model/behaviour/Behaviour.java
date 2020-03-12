package model.behaviour;

import model.Boid;
import model.Vector;
import model.World;
import model.behaviour.rules.Rule;

import java.io.Serializable;
import java.util.ArrayList;

// Represents a boid behaviour having a set of rules that effect a boids acceleration
public class Behaviour implements Serializable {
    private ArrayList<Rule> rules;

    public Behaviour() {
        rules = new ArrayList<>();
    }

    /**
     * EFFECTS: updates the given boid's acceleration by applying each rule
     * MODIFIES: this
     */
    public void update(Boid boid, World world) {
        Vector forces = new Vector(0, 0);

        for (Rule rule : rules) {
            if (rule.isEnabled()) {
                forces.add(rule.update(boid, world));
            }
        }

        boid.addForce(forces);
        boid.move();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
}
