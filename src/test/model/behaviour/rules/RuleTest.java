package model.behaviour.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class RuleTest {
    protected Rule rule;

    @Test
    void testEnabled() {
        rule.setEnabled(false);
        assertFalse(rule.isEnabled());
    }

    @Test
    void testWeight() {
        rule.setWeight(1);
        assertEquals(1, rule.getWeight());
    }
}
