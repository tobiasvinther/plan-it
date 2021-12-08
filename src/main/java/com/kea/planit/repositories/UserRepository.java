package com.kea.planit.repositories;

import com.kea.planit.models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private static UserRepository singe_UR = null;
    private static ArrayList<UserModel> userModels = new ArrayList<>();
    public static UserRepository getInstance(){
        if(singe_UR == null){
            singe_UR = new UserRepository();
        }
        return singe_UR;
    }

    //Dummy Class for now
    public ArrayList<UserModel> getAllUsers(){
        UserModel testUserModel = new UserModel("Test","Test@mail.com","Test123");
        UserModel secondUserModel = new UserModel("Test2","Test2@mail.com","Test231");
        userModels.add(testUserModel);
        userModels.add(secondUserModel);
        return userModels;
    }

    public void addUser(UserModel u){
        userModels.add(u);
    }

    public UserModel findByEmail(String email){
        for(UserModel u: getAllUsers()){
            System.out.println("Do I get here?");
            if(u.getEmail().contains(email)){
                System.out.println("I am here");
                return u;
            }
        }
        return null;
    }
}
