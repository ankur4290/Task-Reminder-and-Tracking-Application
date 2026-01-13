package com.taskreminder.controller;

import com.taskreminder.service.CsvExportService;
import com.taskreminder.service.TaskService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final TaskService taskService;
    private final CsvExportService csvExportService;

    public ReportController(TaskService taskService,
                            CsvExportService csvExportService) {
        this.taskService = taskService;
        this.csvExportService = csvExportService;
    }

    @GetMapping("/overview")
    public Object overview() {
        return taskService.getTaskOverview();
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportCsv() throws IOException {

        File file = csvExportService.exportTasksToCsv(
                taskService.getAllTasks()
        );

        byte[] fileContent = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename(file.getName())
                        .build()
        );

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
