package com.taskreminder.service;

import com.taskreminder.model.Task;
import com.taskreminder.repository.TaskRepository;
import com.taskreminder.scheduler.TaskSchedulerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.taskreminder.model.TaskStatus;


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

    public Task markCompleted(long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        Task task = optionalTask.get();
        task.setCompleted(true);
        taskRepository.update(task);

        return task;
    }

    public Map<String, Long> getTaskOverview() {
        List<Task> tasks = getAllTasks();

        long total = tasks.size();
        long completed = tasks.stream()
                .filter(Task::isCompleted)
                .count();

        long pending = total - completed;

        Map<String, Long> overview = new HashMap<>();
        overview.put("total", total);
        overview.put("completed", completed);
        overview.put("pending", pending);

        return overview;
    }

    public TaskStatus getTaskStatus(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return new TaskStatus(
                task.getId(),
                task.isCompleted(),
                task.getDueDate()
        );
    }

}
