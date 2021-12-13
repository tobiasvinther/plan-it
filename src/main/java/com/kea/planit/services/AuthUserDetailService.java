package com.kea.planit.services;

import com.kea.planit.models.UserModel;
import com.kea.planit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepository UR;

    @Autowired
    public AuthUserDetailService(UserRepository UR){
        this.UR = UR;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        final UserModel userModel = UR.findByEmail(email);
        System.out.println(userModel);
        if( userModel == null){
            System.out.println("Here");
            throw new UsernameNotFoundException(email);
        }
        UserDetails userDetails = User
                .withUsername(userModel.getEmail())
                .password(userModel.getPassword())
                .authorities("USER").build();
        System.out.println(userDetails);
        return userDetails;
    }

}
