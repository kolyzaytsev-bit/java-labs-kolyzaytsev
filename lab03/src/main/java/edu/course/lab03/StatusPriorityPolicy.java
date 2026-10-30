package edu.course.lab03;

public class StatusPriorityPolicy implements PriorityPolicy {

    @Override
    public int calculatePriority(ProjectTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task must not be null");
        }

        return switch (task.getStatus()) {
            case NEW -> 2;
            case IN_PROGRESS -> 1;
            case DONE -> 0;
        };
    }
}