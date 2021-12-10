package com.kea.planit.models;

import java.time.LocalDate;
import java.util.Date;

//Author: Tobias Vinther

public class Task {

    private int id;
    private String name;
    private String description;
    private int hours;
    private String status;
    private Date deadline;
    private int taskOwner;

    public Task(int id, String name, String description, int hours, String status, Date deadline, int taskOwner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.status = status;
        this.deadline = deadline;
        this.taskOwner = taskOwner;
    }

    //constructor without id
    public Task(String name, String description, int hours, String status, Date deadline, int taskOwner) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.status = status;
        this.deadline = deadline;
        this.taskOwner = taskOwner;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(int taskOwner) {
        this.taskOwner = taskOwner;
    }
}
