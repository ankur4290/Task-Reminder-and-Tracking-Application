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
            Executors.newScheduledThreadPool(1);

    public void scheduleReminder(Task task) {
        if (task.getDueDate() == null) return;

        long delay = Duration.between(
                LocalDateTime.now(),
                task.getDueDate()
        ).toMillis();

        if (delay <= 0) return;

        scheduler.schedule(
                () -> System.out.println(
                        " REMINDER: Task '" + task.getTitle() + "' is due!"
                ),
                delay,
                TimeUnit.MILLISECONDS
        );
    }
}
