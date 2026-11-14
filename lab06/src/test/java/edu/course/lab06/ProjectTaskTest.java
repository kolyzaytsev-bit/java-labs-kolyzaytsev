package edu.course.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectTaskTest {

    private static final ProjectTask TASK =
            new ProjectTask(10, "Auth", "backend", "jira", TaskStatus.NEW, 8);

    @Test
    void gettersExposeConstructorValues() {
        assertEquals(10, TASK.getId());
        assertEquals("Auth", TASK.getTitle());
        assertEquals("backend", TASK.getCategory());
        assertEquals("jira", TASK.getSource());
        assertEquals(TaskStatus.NEW, TASK.getStatus());
        assertEquals(8, TASK.getLaborIntensity());
    }

    @Test
    void equalityById() {
        ProjectTask same = new ProjectTask(10, "X", "y", "z", TaskStatus.DONE, 1);
        ProjectTask other = new ProjectTask(11, "Auth", "backend", "jira", TaskStatus.NEW, 8);
        assertEquals(TASK, same);
        assertEquals(TASK.hashCode(), same.hashCode());
        assertNotEquals(TASK, other);
    }
}