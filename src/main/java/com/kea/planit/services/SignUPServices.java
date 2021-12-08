package com.kea.planit.services;

import com.kea.planit.models.UserModel;
import com.kea.planit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUPServices {

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean isUserValid(UserModel userModel){
        return isEmailValid(userModel.getEmail());
    }

    public boolean isEmailValid(String email){
        for(UserModel u: UserRepository.getInstance().getAllUsers()){
            if(u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void register(UserModel userModel){
        encodePassword(userModel);
        UserRepository.getInstance().addUser(userModel);
    }


    private void encodePassword(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    }


}
