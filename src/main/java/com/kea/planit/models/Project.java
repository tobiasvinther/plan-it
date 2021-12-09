package com.kea.planit.models;

import java.time.LocalDate;
import java.util.Date;

public class Project {

    private int id;
    private String name;
    //private String description;
    //private int hoursInAll;
    private String status;
    private Date deadline;
    private int projectOwner;


    public Project(int id, String name, Date deadline, int projectOwner) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.projectOwner = projectOwner;
    }

    public Project(String name, String status, java.sql.Date deadline, int projectOwner) {
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.projectOwner = projectOwner;
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

    /*public String getDescription() {
        return description;
    }*/

    /*public void setDescription(String description) {
        this.description = description;
    }*/

    /*public int getHoursInAll() {
        return hoursInAll;
    }

    public void setHoursInAll(int hoursInAll) {
        this.hoursInAll = hoursInAll;
    }*/

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

    public int getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(int projectOwner) {
        this.projectOwner = projectOwner;
    }
}
