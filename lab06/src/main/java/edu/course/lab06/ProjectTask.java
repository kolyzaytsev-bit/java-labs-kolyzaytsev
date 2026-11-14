package edu.course.lab06;

import java.util.Objects;

public final class ProjectTask {
    private final long id;
    private final String title;
    private final String category;
    private final String source;
    private final TaskStatus status;
    private final int laborIntensity;

    public ProjectTask(long id,
                       String title,
                       String category,
                       String source,
                       TaskStatus status,
                       int laborIntensity) {
        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.category = Objects.requireNonNull(category);
        this.source = Objects.requireNonNull(source);
        this.status = Objects.requireNonNull(status);
        this.laborIntensity = laborIntensity;
    }

    public long getId()               { return id; }
    public String getTitle()          { return title; }
    public String getCategory()       { return category; }
    public String getSource()         { return source; }
    public TaskStatus getStatus()     { return status; }
    public int getLaborIntensity()    { return laborIntensity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTask other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProjectTask{id=" + id
                + ", title='" + title + '\''
                + ", category='" + category + '\''
                + ", status=" + status
                + ", laborIntensity=" + laborIntensity
                + '}';
    }
}