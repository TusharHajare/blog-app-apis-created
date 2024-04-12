package com.example.blogappapis.controllers;

import com.example.blogappapis.payloads.ApiResponce;
import com.example.blogappapis.payloads.UserDto;
import com.example.blogappapis.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private userService userServices;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userServices.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer uid)
    {
        UserDto updatedUesr = userServices.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUesr);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponce> deleteUser(@Valid @PathVariable("id") Integer id)
    {
        this.userServices.deleteUser(id);
        return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUSers()
    {
        return ResponseEntity.ok(this.userServices.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUSer(@Valid @PathVariable Integer id)
    {
        return ResponseEntity.ok(this.userServices.getUserById(id));
    }


}
