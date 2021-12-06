package com.kea.planit.models;

import java.time.LocalDate;
import java.util.Date;

public class Project {

    private int id;
    private String name;
    private String description;
    private int hoursInAll;
    private String status;
    private Date deadline;

    public Project(int id, String name, String description, int hoursInAll, String status, Date deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hoursInAll = hoursInAll;
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

    public int getHoursInAll() {
        return hoursInAll;
    }

    public void setHoursInAll(int hoursInAll) {
        this.hoursInAll = hoursInAll;
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
}
