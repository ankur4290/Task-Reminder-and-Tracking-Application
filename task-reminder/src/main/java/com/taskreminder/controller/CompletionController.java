package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.model.TaskStatus;
import com.taskreminder.repository.TaskRepository;
import com.taskreminder.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Task Completion",
        description = "APIs for marking tasks as completed and checking task status"
)
@RestController
@RequestMapping("/completion")
public class CompletionController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public CompletionController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @Operation(
            summary = "Mark task as completed",
            description = "Marks a task as completed using the task ID"
    )
    @PutMapping("/mark/{id}")
    public Task markCompleted(
            @Parameter(description = "ID of the task to mark as completed")
            @PathVariable long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        taskRepository.update(task);
        return task;
    }

    @Operation(
            summary = "Get task status",
            description = "Returns the current status of a task (COMPLETED or PENDING)"
    )
    @GetMapping("/status/{taskId}")
    public TaskStatus getStatus(
            @Parameter(description = "ID of the task")
            @PathVariable Long taskId) {

        return taskService.getTaskStatus(taskId);
    }
}
