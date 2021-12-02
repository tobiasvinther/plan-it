package com.kea.planit.services;

import com.kea.planit.models.User;
import com.kea.planit.repositories.UserRepository;

public class SignUPServices {


    public boolean isUserValid(User user){
        if(UserRepository.getInstance().getAllUsers().contains(user)){
            return true;
        }
        return false;
    }
}
