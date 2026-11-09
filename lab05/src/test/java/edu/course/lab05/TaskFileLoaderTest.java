package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskFileLoaderTest {

    private static final String HEADER = "id;title;category;estimatedHours;status";

    @TempDir
    Path tempDir;

    private final TaskFileLoader loader = new TaskFileLoader();

    private Path writeFile(String... lines) throws IOException {
        Path file = tempDir.resolve("tasks.csv");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsValidFile() throws IOException {
        Path file = writeFile(HEADER, "T-101;Prepare release;backend;8;READY");

        LoadResult result = loader.load(file);

        assertEquals(1, result.getTasks().size());
        assertTrue(result.getErrors().isEmpty());
        Task task = result.getTasks().get(0);
        assertEquals("T-101", task.getId());
        assertEquals("Prepare release", task.getTitle());
        assertEquals("backend", task.getCategory());
        assertEquals(8, task.getEstimatedHours());
        assertEquals(TaskStatus.READY, task.getStatus());
    }

    @Test
    void loadsUnicodeText() throws IOException {
        Path file = writeFile(HEADER, "T-102;Подготовить релиз;бэкенд;3;IN_PROGRESS");

        LoadResult result = loader.load(file);

        assertEquals(1, result.getTasks().size());
        assertTrue(result.getErrors().isEmpty());
        assertEquals("Подготовить релиз", result.getTasks().get(0).getTitle());
        assertEquals("бэкенд", result.getTasks().get(0).getCategory());
        assertEquals(TaskStatus.IN_PROGRESS, result.getTasks().get(0).getStatus());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = writeFile(HEADER, "T-103;;backend;4;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.getTasks().isEmpty());
        assertEquals(1, result.getErrors().size());
        ValidationError error = result.getErrors().get(0);
        assertEquals(2, error.getLineNumber());
        assertEquals("title", error.getField());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = writeFile(HEADER, "T-104;Task;backend;abc;READY");

        LoadResult result = loader.load(file);

        assertTrue(result.getTasks().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
        assertEquals("estimatedHours", result.getErrors().get(0).getField());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = writeFile(HEADER, "T-105;Task;backend;5;SHIPPED");

        LoadResult result = loader.load(file);

        assertTrue(result.getTasks().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongColumnCount() throws IOException {
        Path file = writeFile(HEADER, "T-106;Task;backend;5");

        LoadResult result = loader.load(file);

        assertTrue(result.getTasks().isEmpty());
        assertEquals(1, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
    }

    @Test
    void keepsValidRowsAndReportsInvalidOnesTogether() throws IOException {
        Path file = writeFile(HEADER,
                "T-201;Good task;backend;2;READY",
                "T-202;;backend;3;READY",
                "T-203;Another good;backend;4;DONE",
                "bad-row");

        LoadResult result = loader.load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals("T-201", result.getTasks().get(0).getId());
        assertEquals("T-203", result.getTasks().get(1).getId());

        assertEquals(2, result.getErrors().size());
        assertEquals(3, result.getErrors().get(0).getLineNumber());
        assertEquals(5, result.getErrors().get(1).getLineNumber());
    }

    @Test
    void throwsOnMissingFile() {
        Path missing = tempDir.resolve("does-not-exist.csv");

        DataLoadingException ex = assertThrows(
                DataLoadingException.class,
                () -> loader.load(missing));

        assertNotNull(ex.getCause());
        assertTrue(ex.getCause() instanceof IOException);
    }

    @Test
    void throwsOnUnexpectedHeader() throws IOException {
        Path file = writeFile("wrong;header;columns", "T-1;X;Y;1;READY");

        assertThrows(DataLoadingException.class, () -> loader.load(file));
    }
}