package com.taskreminder.controller;

import com.taskreminder.service.CsvExportService;
import com.taskreminder.service.TaskService;
import org.springframework.web.bind.annotation.*;

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
    public String exportCsv() {
        return csvExportService.exportTasksToCsv(
                taskService.getAllTasks()
        );
    }
}
