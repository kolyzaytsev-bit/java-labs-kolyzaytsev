package edu.course.lab05;

import java.util.Objects;

public final class Task {

    private final String id;
    private final String title;
    private final String category;
    private final int estimatedHours;
    private final TaskStatus status;

    public Task(String id, String title, String category, int estimatedHours, TaskStatus status) {
        this.id = Objects.requireNonNull(id, "id");
        this.title = Objects.requireNonNull(title, "title");
        this.category = Objects.requireNonNull(category, "category");
        this.estimatedHours = estimatedHours;
        this.status = Objects.requireNonNull(status, "status");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{id='" + id + "', title='" + title + "', category='" + category
                + "', estimatedHours=" + estimatedHours + ", status=" + status + '}';
    }
}