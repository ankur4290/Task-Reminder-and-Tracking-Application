package com.taskreminder.scheduler;

import com.taskreminder.model.Task;
import com.taskreminder.service.EmailService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TaskSchedulerService {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private final EmailService emailService;

    public TaskSchedulerService(EmailService emailService) {
        this.emailService = emailService;
    }



    private final Map<Long, LocalDateTime> reminders = new ConcurrentHashMap<>();
    private final Map<Long, Boolean> reminderTriggered = new ConcurrentHashMap<>();


    public void scheduleReminder(Task task) {
        if (task.getDueDate() == null) return;

        long delay = Duration.between(
                LocalDateTime.now(),
                task.getDueDate()
        ).toMillis();

        if (delay <= 0) return;

        reminders.put(task.getId(), task.getDueDate());

        reminderTriggered.put(task.getId(), false);
        scheduler.schedule(
                () -> {
                    System.out.println(
                            "REMINDER: Task '" + task.getTitle() + "' is due!"
                    );

                    emailService.sendReminderEmail(
                            "test@example.com",
                            "Task Reminder",
                            "Task '" + task.getTitle() + "' is due now."
                    );

                    reminderTriggered.put(task.getId(), true);
                },
                delay,
                TimeUnit.MILLISECONDS
        );

    }

    public LocalDateTime getReminderTime(long taskId) {
        return reminders.get(taskId);

    }

    public String getReminderStatus(long taskId) {
        if (!reminders.containsKey(taskId)) {
            return "No reminder scheduled for this task";
        }

        Boolean triggered = reminderTriggered.get(taskId);

        if (Boolean.TRUE.equals(triggered)) {
            return "Reminder already triggered";
        }

        return "Reminder is scheduled and pending";
    }

}
