package com.kea.planit.services;

import com.kea.planit.models.UserModel;
import com.kea.planit.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public AuthenticationService(){}

    public int findUserId(Authentication authentication){
        UserModel u = UserRepository.getInstance().findByEmail(authentication.getName());
        int id = u.getId();
        System.out.println(id);
        return id;
    }
}
