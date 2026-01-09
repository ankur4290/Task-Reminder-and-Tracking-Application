package com.taskreminder.service;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class CsvExportService {

    public String exportTasksToCsv(List<Task> tasks) {
        String fileName = "tasks-report-" + System.currentTimeMillis() + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("ID,Title,Description,DueDate,Completed\n");

            for (Task task : tasks) {
                writer.write(
                        task.getId() + "," +
                                "\"" + task.getTitle() + "\"," +
                                "\"" + task.getDescription() + "\"," +
                                "\"" + (task.getDueDate() != null ? task.getDueDate() : "") + "\"," +
                                task.isCompleted() + "\n"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV export failed");
        }

        return fileName;
    }
}
