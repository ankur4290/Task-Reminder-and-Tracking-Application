package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import com.taskreminder.scheduler.TaskSchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(
        name = "Scheduling",
        description = "APIs for scheduling and managing task reminders"
)
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final TaskRepository taskRepository;
    private final TaskSchedulerService schedulerService;

    public ScheduleController(TaskRepository taskRepository,
                              TaskSchedulerService schedulerService) {
        this.taskRepository = taskRepository;
        this.schedulerService = schedulerService;
    }

    @Operation(summary = "Schedule a reminder for a task")
    @PostMapping("/set")
    public String setSchedule(@RequestBody Map<String, Object> request) {
        long taskId = Long.parseLong(request.get("taskId").toString());
        LocalDateTime dueDate = LocalDateTime.parse(request.get("dueDate").toString());

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setDueDate(dueDate);
        taskRepository.update(task);
        schedulerService.scheduleReminder(task);

        return "Reminder scheduled successfully";
    }

    @Operation(summary = "Get reminder status for a task")
    @GetMapping("/reminders/{taskId}")
    public String getReminder(@PathVariable long taskId) {
        return schedulerService.getReminderStatus(taskId);
    }
}
