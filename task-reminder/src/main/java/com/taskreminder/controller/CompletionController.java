package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.model.TaskStatus;
import com.taskreminder.repository.TaskRepository;
import com.taskreminder.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/completion")
public class CompletionController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public CompletionController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @PutMapping("/mark/{id}")
    public Task markCompleted(@PathVariable long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(true);
        taskRepository.update(task);
        return task;
    }

    @GetMapping("/status/{taskId}")
    public TaskStatus getStatus(@PathVariable Long taskId) {
        return taskService.getTaskStatus(taskId);
    }
}
