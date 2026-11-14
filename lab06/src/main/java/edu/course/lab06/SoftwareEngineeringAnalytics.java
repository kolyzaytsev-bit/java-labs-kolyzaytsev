package edu.course.lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SoftwareEngineeringAnalytics {

    private final List<ProjectTask> tasks;

    public SoftwareEngineeringAnalytics(List<ProjectTask> tasks) {
        // защитная копия: исходный список снаружи не изменится
        this.tasks = List.copyOf(tasks);
    }

    /** Число задач по статусам. EnumMap — компактно и быстрее HashMap для enum-ключей. */
    public Map<TaskStatus, Integer> countByStatus() {
        Map<TaskStatus, Integer> result = new EnumMap<>(TaskStatus.class);
        for (ProjectTask task : tasks) {
            result.merge(task.getStatus(), 1, Integer::sum);
        }
        return result;
    }

    /** Уникальные категории. LinkedHashSet сохраняет порядок первого появления. */
    public Set<String> uniqueCategories() {
        Set<String> result = new LinkedHashSet<>();
        for (ProjectTask task : tasks) {
            result.add(task.getCategory());
        }
        return result;
    }

    /** Новый список, отсортированный по трудозатратам, затем по id. Исходный список не меняется. */
    public List<ProjectTask> sortedByLaborAndId() {
        List<ProjectTask> copy = new ArrayList<>(tasks);
        copy.sort(Comparator
                .comparingInt(ProjectTask::getLaborIntensity)
                .thenComparingLong(ProjectTask::getId));
        return copy;
    }
}