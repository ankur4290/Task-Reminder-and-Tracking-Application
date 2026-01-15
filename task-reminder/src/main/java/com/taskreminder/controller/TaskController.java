package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(
        name = "Tasks",
        description = "Task creation, retrieval, completion, and overview APIs"
)
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get task overview (total, completed, pending)")
    @GetMapping("/overview")
    public Map<String, Long> getTaskOverview() {
        return taskService.getTaskOverview();
    }

    @Operation(summary = "Mark a task as completed")
    @PutMapping("/{id}/complete")
    public Task markTaskCompleted(@PathVariable long id) {
        return taskService.markCompleted(id);
    }

    @Operation(summary = "Dummy test endpoint")
    @GetMapping("/dummy")
    public String dummy() {
        return "Hello";
    }
}
