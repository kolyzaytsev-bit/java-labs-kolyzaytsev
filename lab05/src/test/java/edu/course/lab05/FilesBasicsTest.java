package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilesBasicsTest {

    @TempDir
    Path tempDir;

    @Test
    void readsNumbersAndDetectsInvalidOne() throws IOException {
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"), StandardCharsets.UTF_8);

        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        assertEquals(3, lines.size());
        assertEquals("10", lines.get(0));
        assertEquals("bad", lines.get(1));
        assertEquals("20", lines.get(2));

        assertThrows(NumberFormatException.class, () -> {
            for (String line : lines) {
                Integer.parseInt(line);
            }
        });
    }
}