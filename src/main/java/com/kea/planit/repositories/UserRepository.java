package com.kea.planit.repositories;

import com.kea.planit.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private static UserRepository singe_UR = null;
    private static ArrayList<User> users = new ArrayList<>();
    public static UserRepository getInstance(){
        if(singe_UR == null){
            singe_UR = new UserRepository();
        }
        return singe_UR;
    }

    //Dummy Class for now
    public ArrayList<User> getAllUsers(){
        User testUser = new User("Test","Test@mail.com","Test123");
        User secondUser = new User("Test2","Test2@mail.com","Test231");
        users.add(testUser);
        users.add(secondUser);
        return users;
    }

    public void addUser(User u){
        users.add(u);
    }

    public User findByEmail(String email){
        for(User u: UserRepository.getInstance().getAllUsers()){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}
