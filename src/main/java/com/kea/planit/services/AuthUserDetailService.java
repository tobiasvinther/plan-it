package com.kea.planit.services;

import com.kea.planit.models.User;
import com.kea.planit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {
    private UserRepository UR;

    @Autowired
    public AuthUserDetailService(UserRepository userRepository){
        this.UR = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        final User user = UR.findByEmail(email);
        if( user == null){
            throw new UsernameNotFoundException(email);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER").build();
        return userDetails;
    }

}
