package com.taskreminder.service;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AtomicLong idCounter = new AtomicLong(1);

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(Task task) {
        task.setId(idCounter.getAndIncrement());
        task.setCompleted(false);
        taskRepository.save(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
