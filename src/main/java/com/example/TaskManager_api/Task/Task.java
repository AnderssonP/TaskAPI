package com.example.TaskManager_api.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Calendar;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Status cannot be null")
    private String status;
    private Date starting;
    @NotNull(message = "Choose a end date for the task")
    private Date ending;


    public Task(String title, String description, String status, Date ending) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.starting = Calendar.getInstance().getTime();
        this.ending = ending;
    }

    public Task(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.starting = Calendar.getInstance().getTime();
    }


    public Task() {
        this.starting = Calendar.getInstance().getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }
}

