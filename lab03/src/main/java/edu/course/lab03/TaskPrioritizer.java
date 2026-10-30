package edu.course.lab03;

public class TaskPrioritizer {
    private final PriorityPolicy priorityPolicy;

    public TaskPrioritizer(PriorityPolicy priorityPolicy) {
        if (priorityPolicy == null) {
            throw new IllegalArgumentException("PriorityPolicy must not be null");
        }
        this.priorityPolicy = priorityPolicy;
    }

    public ProjectTask selectMorePrioritized(ProjectTask first, ProjectTask second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Tasks must not be null");
        }

        int firstPriority = priorityPolicy.calculatePriority(first);
        int secondPriority = priorityPolicy.calculatePriority(second);

        return firstPriority >= secondPriority ? first : second;
    }
}