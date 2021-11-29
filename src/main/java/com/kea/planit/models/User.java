package com.kea.planit.models;

import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private final ArrayList<Task> TASK_LIST = new ArrayList<>();

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
