package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/overview")
    public Map<String, Long> getTaskOverview() {
        return taskService.getTaskOverview();
    }

    @PutMapping("/{id}/complete")
    public Task markTaskCompleted(@PathVariable long id) {
        return taskService.markCompleted(id);
    }


    @GetMapping("/dummy")
    public String dummy() {
        return "Hello";
    }


}
