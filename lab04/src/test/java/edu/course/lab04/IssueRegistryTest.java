package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IssueRegistryTest {
    @Test
    void equalButDistinctKeyFindsStoredValue() {
        IssueRegistry registry = new IssueRegistry();
        registry.put(new IssueId("PROJ", 1), new ProjectTask("Fix bug", "Alice"));

        ProjectTask found = registry.get(new IssueId("PROJ", 1)).orElseThrow();
        assertEquals("Fix bug", found.getTitle());
        assertEquals("Alice", found.getAssignee());
    }

    @Test
    void reinsertWithEqualKeyReplacesValueAndSizeUnchanged() {
        IssueRegistry registry = new IssueRegistry();
        IssueId first = new IssueId("PROJ", 1);
        registry.put(first, new ProjectTask("Old", "Alice"));
        registry.put(new IssueId("PROJ", 1), new ProjectTask("New", "Bob"));

        assertEquals(1, registry.size());
        ProjectTask task = registry.get(first).orElseThrow();
        assertEquals("New", task.getTitle());
        assertEquals("Bob", task.getAssignee());
    }

    @Test
    void differentKeysKeepDifferentValues() {
        IssueRegistry registry = new IssueRegistry();
        registry.put(new IssueId("PROJ", 1), new ProjectTask("t1", "a"));
        registry.put(new IssueId("PROJ", 2), new ProjectTask("t2", "b"));

        assertEquals(2, registry.size());
        assertEquals("t1", registry.get(new IssueId("PROJ", 1)).orElseThrow().getTitle());
        assertEquals("t2", registry.get(new IssueId("PROJ", 2)).orElseThrow().getTitle());
    }

    @Test
    void removeAndContainsWorkAsExpected() {
        IssueRegistry registry = new IssueRegistry();
        IssueId key = new IssueId("PROJ", 1);
        registry.put(key, new ProjectTask("T", "A"));

        assertTrue(registry.contains(key));
        assertTrue(registry.remove(key).isPresent());

        assertFalse(registry.contains(key));
        assertEquals(0, registry.size());
        assertTrue(registry.get(key).isEmpty());
        assertTrue(registry.remove(key).isEmpty());
    }

    @Test
    void putReturnsPreviousValueOnReplace() {
        IssueRegistry registry = new IssueRegistry();
        IssueId key = new IssueId("PROJ", 1);

        assertTrue(registry.put(key, new ProjectTask("A", "x")).isEmpty());
        ProjectTask previous = registry.put(new IssueId("PROJ", 1),
                new ProjectTask("B", "y")).orElseThrow();
        assertEquals("A", previous.getTitle());
    }
}