package com.kea.planit.repositories;

import com.kea.planit.models.User;

import java.util.ArrayList;

public class UserRepository {

    //Dummy Class for now
    public ArrayList<User> getAllUsers(){
        ArrayList<User> userList = new ArrayList<User>();
        User testUser = new User(1,"Test","Test@mail.com","Test123");
        User secondUser = new User(2,"Test2","Test2@mail.com","Test231");
        userList.add(testUser);
        userList.add(secondUser);
        return userList;
    }

}
