package com.taskreminder.service;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Task addTask(Task task) {
        task.setId(idCounter.getAndIncrement());
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
