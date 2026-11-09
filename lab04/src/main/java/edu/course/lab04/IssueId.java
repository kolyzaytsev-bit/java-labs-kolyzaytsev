package edu.course.lab04;

import java.util.Objects;

public final class IssueId {
    private final String projectCode;
    private final int number;

    public IssueId(String projectCode, int number) {
        if (projectCode == null || projectCode.isBlank()) {
            throw new IllegalArgumentException("projectCode must be non-blank");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("number must be positive");
        }
        this.projectCode = projectCode;
        this.number = number;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueId)) {
            return false;
        }
        IssueId other = (IssueId) o;
        return number == other.number && projectCode.equals(other.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, number);
    }

    @Override
    public String toString() {
        return projectCode + "-" + number;
    }
}