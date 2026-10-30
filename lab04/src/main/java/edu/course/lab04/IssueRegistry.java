package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class IssueRegistry {
    private final Map<IssueId, ProjectTask> tasks = new HashMap<>();

    public Optional<ProjectTask> put(IssueId id, ProjectTask task) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(task, "task");
        return Optional.ofNullable(tasks.put(id, task));
    }

    public Optional<ProjectTask> get(IssueId id) {
        Objects.requireNonNull(id, "id");
        return Optional.ofNullable(tasks.get(id));
    }

    public Optional<ProjectTask> remove(IssueId id) {
        Objects.requireNonNull(id, "id");
        return Optional.ofNullable(tasks.remove(id));
    }

    public boolean contains(IssueId id) {
        Objects.requireNonNull(id, "id");
        return tasks.containsKey(id);
    }

    public int size() {
        return tasks.size();
    }
}