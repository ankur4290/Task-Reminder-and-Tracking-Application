package com.taskreminder.service;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvExportService {

    public File exportTasksToCsv(List<Task> tasks) {

        String fileName = "tasks_" + System.currentTimeMillis() + ".csv";
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file)) {

            writer.append("ID,Title,Description,Due Date,Completed\n");

            for (Task task : tasks) {
                writer.append(task.getId().toString()).append(",");
                writer.append(task.getTitle()).append(",");
                writer.append(task.getDescription()).append(",");
                writer.append(String.valueOf(task.getDueDate())).append(",");
                writer.append(String.valueOf(task.isCompleted())).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to export CSV", e);
        }

        return file;
    }
}
