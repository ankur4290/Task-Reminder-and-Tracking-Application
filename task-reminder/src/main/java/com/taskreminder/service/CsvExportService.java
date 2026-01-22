package com.taskreminder.service;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CsvExportService {

    public File exportTasksToCsv(List<Task> tasks) {

        try {
            String exportDir = "exports";
            Files.createDirectories(Paths.get(exportDir));

            String fileName = "tasks_" + System.currentTimeMillis() + ".csv";
            Path filePath = Paths.get(exportDir, fileName);

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

                writer.write("ID,Title,Description,Due Date,Completed\n");

                for (Task task : tasks) {
                    writer.write(task.getId() + ",");
                    writer.write(task.getTitle() + ",");
                    writer.write(task.getDescription() + ",");
                    writer.write(task.getDueDate() + ",");
                    writer.write(task.isCompleted() + "\n");
                }
            }

            return filePath.toFile();

        } catch (IOException e) {
            throw new RuntimeException("Failed to export CSV", e);
        }
    }
}
