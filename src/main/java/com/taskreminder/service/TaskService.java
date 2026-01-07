package com.taskreminder.service;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Task addTask(Task task) {
        long id = idCounter.getAndIncrement();
        task.setId(id);
        task.setCompleted(false);
        tasks.put(id, task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
}
