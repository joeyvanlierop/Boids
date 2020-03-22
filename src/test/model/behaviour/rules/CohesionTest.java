package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CohesionTest extends RuleTest {
    private World world;
    private Boid boid1;
    private Boid boid2;
    private Boid boid3;

    @BeforeEach
    void runBefore() {
        rule = new Cohesion(true, 1, 10);
        world = new World.WorldBuilder().setWidth(100).setHeight(100).addRule(rule).build();
        boid1 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(2, 0)).setVelocity(new Vector(0, 1)).build());
        boid2 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 2)).setVelocity(new Vector(0, 1)).build());
        boid3 = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(50, 50)).setVelocity(new Vector(0, 2)).build());
    }

    @Test
    void testCohesion() {
        assertEquals(new Vector(-2, 2).norm().sub(boid1.getVelocity()).norm(), rule.update(boid1, world));
        assertEquals(new Vector(2, -2).norm().sub(boid2.getVelocity()).norm(), rule.update(boid2, world));
    }

    @Test
    void testCohesionNotInSight() {
        world.setViewAngle(90);

        assertEquals(new Vector(-2, 2).norm().sub(boid1.getVelocity()).norm(), rule.update(boid1, world));
        assertEquals(new Vector(0, 0), rule.update(boid2, world));
    }

    @Test
    void testCohesionNotInRange() {
        assertEquals(new Vector(0, 0), rule.update(boid3, world));
    }
}
