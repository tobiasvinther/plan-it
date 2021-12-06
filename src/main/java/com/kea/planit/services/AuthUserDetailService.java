package com.kea.planit.services;

import com.kea.planit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository UR;

    @Override
    public UserDetails loadUserByUsername(String emial) throws UsernameNotFoundException{
        final User user = UR.
    }

}
