package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityPolicyTest {

    @Test
    void effortPolicyUsesEffort() {
        PriorityPolicy policy = new EffortPriorityPolicy();

        ProjectTask task = new ProjectTask("Task", 7, ProjectTask.Status.NEW);

        assertEquals(7, policy.calculatePriority(task));
    }

    @Test
    void effortPolicyReturnsZeroForZeroEffort() {
        PriorityPolicy policy = new EffortPriorityPolicy();

        ProjectTask task = new ProjectTask("Task", 0, ProjectTask.Status.DONE);

        assertEquals(0, policy.calculatePriority(task));
    }

    @Test
    void statusPolicyUsesStatus() {
        PriorityPolicy policy = new StatusPriorityPolicy();

        assertEquals(2, policy.calculatePriority(
                new ProjectTask("New", 1, ProjectTask.Status.NEW)
        ));
        assertEquals(1, policy.calculatePriority(
                new ProjectTask("In progress", 1, ProjectTask.Status.IN_PROGRESS)
        ));
        assertEquals(0, policy.calculatePriority(
                new ProjectTask("Done", 1, ProjectTask.Status.DONE)
        ));
    }

    @Test
    void statusPolicyRejectsNullTask() {
        PriorityPolicy policy = new StatusPriorityPolicy();

        assertThrows(IllegalArgumentException.class, () -> policy.calculatePriority(null));
    }

    @Test
    void prioritizerSelectsTaskWithHigherEffort() {
        ProjectTask smallTask = new ProjectTask("Small", 2, ProjectTask.Status.NEW);
        ProjectTask largeTask = new ProjectTask("Large", 10, ProjectTask.Status.DONE);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());

        assertSame(largeTask, prioritizer.selectMorePrioritized(smallTask, largeTask));
    }

    @Test
    void prioritizerCanBeReusedWithAnotherPolicyWithoutChangingIt() {
        ProjectTask largeDoneTask = new ProjectTask("Large done", 10, ProjectTask.Status.DONE);
        ProjectTask smallNewTask = new ProjectTask("Small new", 1, ProjectTask.Status.NEW);

        TaskPrioritizer effortPrioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        TaskPrioritizer statusPrioritizer = new TaskPrioritizer(new StatusPriorityPolicy());

        assertSame(largeDoneTask,
                effortPrioritizer.selectMorePrioritized(largeDoneTask, smallNewTask));
        assertSame(smallNewTask,
                statusPrioritizer.selectMorePrioritized(largeDoneTask, smallNewTask));
    }

    @Test
    void prioritizerDoesNotModifyInputTasks() {
        ProjectTask first = new ProjectTask("First", 3, ProjectTask.Status.NEW);
        ProjectTask second = new ProjectTask("Second", 5, ProjectTask.Status.DONE);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        prioritizer.selectMorePrioritized(first, second);

        assertEquals("First", first.getTitle());
        assertEquals(3, first.getEffort());
        assertEquals(ProjectTask.Status.NEW, first.getStatus());

        assertEquals("Second", second.getTitle());
        assertEquals(5, second.getEffort());
        assertEquals(ProjectTask.Status.DONE, second.getStatus());
    }
}