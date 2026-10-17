package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTaskTest {

    @Test
    void createsValidTask() {
        ProjectTask task = new ProjectTask("1", "Do lab", TaskStatus.NEW, 4);

        assertEquals("1", task.getId());
        assertEquals("Do lab", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(4, task.getEstimatedHours());
        assertFalse(task.isCompleted());
    }

    @Test
    void rejectsBlankId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask(" ", "Do lab", TaskStatus.NEW, 4)
        );
    }

    @Test
    void rejectsBlankTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("1", " ", TaskStatus.NEW, 4)
        );
    }

    @Test
    void rejectsNullStatus() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("1", "Do lab", null, 4)
        );
    }

    @Test
    void rejectsNonPositiveEstimatedHours() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProjectTask("1", "Do lab", TaskStatus.NEW, 0)
        );
    }

    @Test
    void changesStatus() {
        ProjectTask task = new ProjectTask("1", "Do lab", TaskStatus.NEW, 4);

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void marksTaskAsCompleted() {
        ProjectTask task = new ProjectTask("1", "Do lab", TaskStatus.NEW, 4);

        task.changeStatus(TaskStatus.DONE);

        assertTrue(task.isCompleted());
    }

    @Test
    void increasesEstimate() {
        ProjectTask task = new ProjectTask("1", "Do lab", TaskStatus.NEW, 4);

        task.increaseEstimate(3);

        assertEquals(7, task.getEstimatedHours());
    }

    @Test
    void rejectsNonPositiveIncreaseEstimate() {
        ProjectTask task = new ProjectTask("1", "Do lab", TaskStatus.NEW, 4);

        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(0));
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimate(-2));
    }
}