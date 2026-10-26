package edu.course.lab03;

public class ProjectTask {
    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE
    }

    private final String title;
    private final int effort;
    private final Status status;

    public ProjectTask(String title, int effort, Status status) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (effort < 0) {
            throw new IllegalArgumentException("Effort must not be negative");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
        this.title = title;
        this.effort = effort;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public int getEffort() {
        return effort;
    }

    public Status getStatus() {
        return status;
    }
}