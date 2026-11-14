package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class SoftwareEngineeringAnalyticsTest {

    private static final ProjectTask T1 = new ProjectTask(1, "Auth", "backend", "jira", TaskStatus.NEW, 8);
    private static final ProjectTask T2 = new ProjectTask(2, "UI",   "frontend", "jira", TaskStatus.IN_PROGRESS, 3);
    private static final ProjectTask T3 = new ProjectTask(3, "DB",   "backend", "tracker", TaskStatus.DONE, 5);
    private static final ProjectTask T4 = new ProjectTask(4, "Docs", "docs",     "wiki",    TaskStatus.NEW, 1);
    private static final ProjectTask T5 = new ProjectTask(5, "Cache","backend",  "jira",    TaskStatus.NEW, 5);

    private final List<ProjectTask> source = List.of(T1, T2, T3, T4, T5);
    private final SoftwareEngineeringAnalytics analytics = new SoftwareEngineeringAnalytics(source);

    @Test
    void countByStatus() {
        Map<TaskStatus, Integer> counts = analytics.countByStatus();
        assertEquals(3, counts.get(TaskStatus.NEW));
        assertEquals(1, counts.get(TaskStatus.IN_PROGRESS));
        assertEquals(1, counts.get(TaskStatus.DONE));
        assertEquals(null, counts.get(TaskStatus.CANCELLED));
    }

    @Test
    void uniqueCategories() {
        Set<String> categories = analytics.uniqueCategories();
        assertEquals(Set.of("backend", "frontend", "docs"), categories);
        assertEquals(3, categories.size());
    }

    @Test
    void sortedByLaborAndId() {
        List<ProjectTask> sorted = analytics.sortedByLaborAndId();
        // ожидаемый порядок: 1,3,5,2,8 => id 4,2,3,5,1
        assertEquals(List.of(T4, T2, T3, T5, T1), sorted);
    }

    @Test
    void sourceListIsNotModified() {
        List<ProjectTask> sorted = analytics.sortedByLaborAndId();
        assertNotSame(source, sorted);
        assertEquals(List.of(T1, T2, T3, T4, T5), source);
    }
}