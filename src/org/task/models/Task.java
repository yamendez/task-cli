package org.task.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int ID;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private static int count = 0;
    private static List<Task> taskList;

    public Task(String description) {
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.status = Status.TODO;
        this.ID = ++count + ID;
        taskList = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public static List<Task> addTask(Task task){
        taskList.add(task);
        return taskList;
    }
}
