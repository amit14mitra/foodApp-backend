package com.example.FoodApp.service;

import com.example.FoodApp.model.Users;
import com.example.FoodApp.model.UserPrinciple;
import com.example.FoodApp.repository.UserReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReporsitory userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found!!");
        }
        return new UserPrinciple(user);
    }
}
