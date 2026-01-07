package com.taskreminder.scheduler;

import com.taskreminder.model.Task;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TaskSchedulerService {

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public void scheduleReminder(Task task) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = task.getDueDate();

        if (dueDate.isBefore(now)) {
            return;
        }

        long delay = Duration.between(now, dueDate).toSeconds();

        scheduler.schedule(() -> {
            System.out.println(" REMINDER: Task '" + task.getTitle() + "' is due now!");
        }, delay, TimeUnit.SECONDS);
    }
}
