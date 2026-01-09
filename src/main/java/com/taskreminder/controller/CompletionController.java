package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/completion")
public class CompletionController {

    private final TaskRepository taskRepository;

    public CompletionController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PutMapping("/mark/{id}")
    public Task markCompleted(@PathVariable long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(true);
        taskRepository.update(task);
        return task;
    }

    @GetMapping("/status/{id}")
    public boolean getStatus(@PathVariable long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"))
                .isCompleted();
    }

}
