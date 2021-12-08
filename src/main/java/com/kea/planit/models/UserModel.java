package com.kea.planit.models;

import java.util.ArrayList;

public class UserModel {

    private int id;
    private String name;
    private String email;
    private String password;
    private final ArrayList<Task> TASK_LIST = new ArrayList<>();
    private static int idcounter = 0;

   public UserModel(){};

    public UserModel(String name, String email, String password) {
        id= idcounter+1;
        idcounter++;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", TASK_LIST=" + TASK_LIST +
                '}';
    }
}