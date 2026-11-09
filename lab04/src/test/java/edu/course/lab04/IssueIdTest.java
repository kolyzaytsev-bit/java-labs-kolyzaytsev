package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IssueIdTest {

    @Test
    void equalWhenBothFieldsMatch() {
        IssueId a = new IssueId("PROJ", 1);
        IssueId b = new IssueId("PROJ", 1);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void notEqualWhenProjectCodeDiffers() {
        assertNotEquals(new IssueId("A", 1), new IssueId("B", 1));
    }

    @Test
    void notEqualWhenNumberDiffers() {
        assertNotEquals(new IssueId("A", 1), new IssueId("A", 2));
    }

    @Test
    void equalsAgainstNullOrOtherTypeIsFalse() {
        IssueId id = new IssueId("PROJ", 1);
        // Обязательный эксперимент №4
        assertFalse(id.equals(null));
        assertFalse(id.equals("PROJ-1"));
        assertFalse(id.equals(Integer.valueOf(1)));
    }

    @Test
    void rejectsInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new IssueId("", 1));
        assertThrows(IllegalArgumentException.class, () -> new IssueId(null, 1));
        assertThrows(IllegalArgumentException.class, () -> new IssueId("P", 0));
    }
}