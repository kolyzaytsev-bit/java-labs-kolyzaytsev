package edu.course.lab05;

import java.util.Objects;

public final class ValidationError {

    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        this.lineNumber = lineNumber;
        this.field = Objects.requireNonNull(field, "field");
        this.message = Objects.requireNonNull(message, "message");
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidationError{line=" + lineNumber
                + ", field='" + field + '\''
                + ", message='" + message + '\'' + '}';
    }
}