package com.example.blogappapis.repositories;

import com.example.blogappapis.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//import org.springframework.stereotype.Repository;
public interface userRepo extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
}
