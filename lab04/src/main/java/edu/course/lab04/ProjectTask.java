package edu.course.lab04;

import java.util.Objects;

public final class ProjectTask {
    private final String title;
    private final String assignee;

    public ProjectTask(String title, String assignee) {
        this.title = Objects.requireNonNull(title, "title");
        this.assignee = Objects.requireNonNull(assignee, "assignee");
    }

    public String getTitle() {
        return title;
    }

    public String getAssignee() {
        return assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectTask)) {
            return false;
        }
        ProjectTask other = (ProjectTask) o;
        return title.equals(other.title) && assignee.equals(other.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, assignee);
    }

    @Override
    public String toString() {
        return "ProjectTask{title='" + title + "', assignee='" + assignee + "'}";
    }
}