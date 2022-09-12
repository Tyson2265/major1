package com.example.major1.Services;

import com.example.major1.Models.CustomUserDetails;
import com.example.major1.Models.Users;
import com.example.major1.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepo.findUsersByEmail(email);
        users.orElseThrow(()-> new UsernameNotFoundException("User not Found"));

        return users.map(CustomUserDetails::new).get();
    }
}
