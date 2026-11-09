package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CollisionExperimentTest {

    private static final class CollidingKey {
        private final String name;

        CollidingKey(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof CollidingKey && name.equals(((CollidingKey) o).name);
        }

        @Override
        public int hashCode() {
            return 42; // форсируем коллизию
        }

        @Override
        public String toString() {
            return "CollidingKey{" + name + "}";
        }
    }

    @Test
    void unequalKeysWithSameHashCodeKeepDistinctValues() {
        CollidingKey a = new CollidingKey("a");
        CollidingKey b = new CollidingKey("b");

        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, b);

        Map<CollidingKey, String> map = new HashMap<>();
        map.put(a, "value-a");
        map.put(b, "value-b");

        assertEquals(2, map.size());
        assertEquals("value-a", map.get(a));
        assertEquals("value-b", map.get(b));
    }
}