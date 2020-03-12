package model.behaviour.rules;

import model.Boid;
import model.Vector;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoiseTest extends RuleTest {
    private World world;
    private Boid boid;

    @BeforeEach
    void runBefore() {
        rule = new Noise(true, 1);
        world = new World.WorldBuilder().setWidth(100).setHeight(100).addRule(rule).build();
        boid = world.addBoid(new Boid.BoidBuilder().setPosition(new Vector(0, 0)).setVelocity(new Vector(0, 0)).build());
    }

    @Test
    void testAlignment() {
        Vector random = rule.update(boid, world);
        assertTrue(random.getX() >= -1 && random.getX() <= 1);
        assertTrue(random.getY() >= -1 && random.getY() <= 1);
    }
}
