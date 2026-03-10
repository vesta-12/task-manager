package models;

import enums.TaskStatus;

public class Task {
    private final String id;
    private final String title;
    private final String description;
    private TaskStatus status;

    public Task(String id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String toFileString() {
        return id + ";" + title + ";" + description + ";" + status;
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + description + ") - " + status;
    }
}