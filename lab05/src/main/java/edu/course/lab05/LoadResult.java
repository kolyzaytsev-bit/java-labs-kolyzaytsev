package edu.course.lab05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LoadResult {

    private final List<Task> tasks;
    private final List<ValidationError> errors;

    public LoadResult(List<Task> tasks, List<ValidationError> errors) {
        this.tasks = Collections.unmodifiableList(new ArrayList<>(tasks));
        this.errors = Collections.unmodifiableList(new ArrayList<>(errors));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}