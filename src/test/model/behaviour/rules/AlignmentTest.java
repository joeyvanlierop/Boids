package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlignmentTest extends RuleTest {
    private World world;
    private Boid boid1;
    private Boid boid2;
    private Boid boid3;

    @BeforeEach
    void runBefore() {
        rule = new Alignment(true, 1, 10);
        world = new World.WorldBuilder().setWidth(100).setHeight(100).addRule(rule).build();
        boid1 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 0)).setVelocity(new Vector(2, 0)).build());
        boid2 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(1, 0)).setVelocity(new Vector(0, 2)).build());
        boid3 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(50, 50)).setVelocity(new Vector(0, 2)).build());
    }

    @Test
    void testAlignment() {
        assertEquals(new Vector(-2, 2).norm(), rule.update(boid1, world));
        assertEquals(new Vector(2, -2).norm(), rule.update(boid2, world));
    }

    @Test
    void testAlignmentNotInSight() {
        world.setViewAngle(45);

        assertEquals(new Vector(-2, 2).norm(), rule.update(boid1, world));
        assertEquals(new Vector(0, 0), rule.update(boid2, world));
    }

    @Test
    void testAlignmentNotInRange() {
        assertEquals(new Vector(0, 0), rule.update(boid3, world));
    }
}
