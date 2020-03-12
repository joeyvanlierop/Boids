package model.behaviour;

import model.Boid;
import model.Vector;
import model.World;
import model.behaviour.rules.Separation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BehaviourTest {
    private Behaviour behaviour;
    private World world;
    private Boid boid1;
    private Boid boid2;

    @BeforeEach
    void runBefore() {
        behaviour = new Behaviour();
        world = new World.WorldBuilder().setWidth(100).setHeight(100).build();
        boid1 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 0)).setVelocity(new Vector(1, 0)).build());
        boid2 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(2, 0)).setVelocity(new Vector(1, 0)).build());
    }

    @Test
    void testAddRule() {
        assertEquals(0, behaviour.getRules().size());
        behaviour.addRule(new Separation(true, 1, 100));
        assertEquals(1, behaviour.getRules().size());
    }

    @Test
    void testUpdateRuleEnabled() {
        behaviour.addRule(new Separation(true, 1, 100));
        behaviour.update(boid1, world);
        behaviour.update(boid2, world);
        assertNotEquals(new Vector(2, 0), boid1.getPosition());
        assertNotEquals(new Vector(3, 0), boid2.getPosition());
    }

    @Test
    void testUpdateRuleNotEnabled() {
        behaviour.addRule(new Separation(false, 1, 100));
        behaviour.update(boid1, world);
        behaviour.update(boid2, world);
        assertEquals(new Vector(2, 0), boid1.getPosition());
        assertEquals(new Vector(3, 0), boid2.getPosition());
    }
}

