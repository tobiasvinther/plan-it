package com.kea.planit.models;

import java.time.LocalDate;
import java.util.Date;

public class Task {

    private int id;
    private String name;
    private String description;
    private int hours;
    private String status;
    private LocalDate deadline;

    public Task(int id, String name, String description, int hours, String status, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.status = status;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
