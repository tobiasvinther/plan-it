package com.kea.planit.services;

import com.kea.planit.models.User;
import com.kea.planit.repositories.UserRepository;

public class SignUPServices {


    public boolean isUserValid(User user){
        if(isEmailValid(user.getEmail())){
            return true;
        }
        return false;
    }

    public boolean isEmailValid(String email){
        for(User u: UserRepository.getInstance().getAllUsers()){
            if(u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
}
