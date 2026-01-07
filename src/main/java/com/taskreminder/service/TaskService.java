package com.taskreminder.service;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import com.taskreminder.scheduler.TaskSchedulerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskSchedulerService schedulerService;
    private final AtomicLong idCounter = new AtomicLong(1);

    public TaskService(TaskRepository taskRepository,
                       TaskSchedulerService schedulerService) {
        this.taskRepository = taskRepository;
        this.schedulerService = schedulerService;
    }

    public Task addTask(Task task) {
        task.setId(idCounter.getAndIncrement());
        task.setCompleted(false);

        taskRepository.save(task);
        schedulerService.scheduleReminder(task);

        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
