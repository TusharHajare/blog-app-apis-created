package com.example.blogappapis.Security;


import com.example.blogappapis.entites.User;
import com.example.blogappapis.exceptions.ResourceNotFoundException;
import com.example.blogappapis.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService
{

    @Autowired
    private userRepo userRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // loading user from database
       User user = this.userRepos.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email:" + username, 0));

       return user;
    }
}
