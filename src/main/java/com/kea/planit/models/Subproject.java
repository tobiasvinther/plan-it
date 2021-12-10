package com.kea.planit.models;

import java.util.Date;

//Author: Tobias Vinther

public class Subproject {

    private int id;
    private String name;
    private Date deadline;
    private int subprojectOwner;

    public Subproject(int id, String name, Date deadline, int subprojectOwner) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.subprojectOwner = subprojectOwner;
    }

    //constructor without id
    public Subproject(String name, Date deadline, int subprojectOwner) {
        this.name = name;
        this.deadline = deadline;
        this.subprojectOwner = subprojectOwner;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getSubprojectOwner() {
        return subprojectOwner;
    }

    public void setSubprojectOwner(int subprojectOwner) {
        this.subprojectOwner = subprojectOwner;
    }
}
