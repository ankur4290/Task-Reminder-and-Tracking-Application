package com.taskreminder.model;

import java.time.LocalDateTime;

public class TaskStatus {

    private Long taskId;
    private boolean completed;
    private String status;
    private LocalDateTime dueDate;

    public TaskStatus(Long taskId, boolean completed, LocalDateTime dueDate) {
        this.taskId = taskId;
        this.completed = completed;
        this.dueDate = dueDate;
        this.status = completed ? "COMPLETED" : "PENDING";
    }

    public Long getTaskId() {
        return taskId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
