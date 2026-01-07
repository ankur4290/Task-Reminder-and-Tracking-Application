package com.taskreminder.controller;

import com.taskreminder.model.Task;
import com.taskreminder.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/list")
    public List<Task> listTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/dummy")
    public  String dummy() {
        return "Hello";
  }
}
