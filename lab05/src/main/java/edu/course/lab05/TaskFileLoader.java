package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TaskFileLoader {

    public static final List<String> EXPECTED_HEADER =
            List.of("id", "title", "category", "estimatedHours", "status");

    private static final int FIELD_COUNT = EXPECTED_HEADER.size();

    public LoadResult load(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("Cannot read " + path, e);
        }

        if (lines.isEmpty()) {
            throw new DataLoadingException("File " + path + " is empty: missing header");
        }

        validateHeader(lines.get(0), path);

        List<Task> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int index = 1; index < lines.size(); index++) {
            String line = lines.get(index);
            if (line.isEmpty()) {
                continue;
            }
            int lineNumber = index + 1;
            parseRow(lineNumber, line, tasks, errors);
        }

        return new LoadResult(tasks, errors);
    }

    private void validateHeader(String header, Path path) {
        String[] raw = header.split(";", -1);
        List<String> actual = new ArrayList<>(raw.length);
        for (String part : raw) {
            actual.add(part.trim());
        }
        if (!actual.equals(EXPECTED_HEADER)) {
            throw new DataLoadingException(
                    "Unexpected header in " + path
                            + ": expected " + EXPECTED_HEADER + " but got " + actual);
        }
    }

    private void parseRow(int lineNumber, String line, List<Task> tasks, List<ValidationError> errors) {
        String[] parts = line.split(";", -1);
        if (parts.length != FIELD_COUNT) {
            errors.add(new ValidationError(lineNumber, "",
                    "expected " + FIELD_COUNT + " fields but got " + parts.length));
            return;
        }

        String id = parts[0].trim();
        String title = parts[1].trim();
        String category = parts[2].trim();
        String hoursRaw = parts[3].trim();
        String statusRaw = parts[4].trim();

        boolean valid = true;

        if (id.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "id", "id must not be empty"));
            valid = false;
        }
        if (title.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "title", "title must not be empty"));
            valid = false;
        }
        if (category.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "category", "category must not be empty"));
            valid = false;
        }

        int estimatedHours = 0;
        if (hoursRaw.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "estimatedHours",
                    "estimatedHours must not be empty"));
            valid = false;
        } else {
            try {
                estimatedHours = Integer.parseInt(hoursRaw);
                if (estimatedHours <= 0) {
                    errors.add(new ValidationError(lineNumber, "estimatedHours",
                            "estimatedHours must be positive: " + estimatedHours));
                    valid = false;
                }
            } catch (NumberFormatException e) {
                errors.add(new ValidationError(lineNumber, "estimatedHours",
                        "estimatedHours is not an integer: " + hoursRaw));
                valid = false;
            }
        }

        TaskStatus status = null;
        if (statusRaw.isEmpty()) {
            errors.add(new ValidationError(lineNumber, "status", "status must not be empty"));
            valid = false;
        } else {
            try {
                status = TaskStatus.valueOf(statusRaw);
            } catch (IllegalArgumentException e) {
                errors.add(new ValidationError(lineNumber, "status",
                        "unknown status: " + statusRaw));
                valid = false;
            }
        }

        if (valid) {
            tasks.add(new Task(id, title, category, estimatedHours, status));
        }
    }
}