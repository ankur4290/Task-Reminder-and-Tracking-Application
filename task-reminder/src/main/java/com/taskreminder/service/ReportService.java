package com.taskreminder.service;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final TaskRepository taskRepository;

    public ReportService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Map<String, Long> getOverview() {
        List<Task> tasks = taskRepository.findAll();

        long total = tasks.size();
        long completed = tasks.stream().filter(Task::isCompleted).count();
        long pending = total - completed;

        Map<String, Long> overview = new HashMap<>();
        overview.put("total", total);
        overview.put("completed", completed);
        overview.put("pending", pending);

        return overview;
    }

    public void exportTasksToCsv() {
        List<Task> tasks = taskRepository.findAll();

        try (FileWriter writer = new FileWriter("tasks.csv")) {
            writer.write("ID,Title,Description,DueDate,Completed\n");

            for (Task task : tasks) {
                writer.write(
                        task.getId() + "," +
                                task.getTitle() + "," +
                                task.getDescription() + "," +
                                task.getDueDate() + "," +
                                task.isCompleted() + "\n"
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to export CSV", e);
        }
    }
}
