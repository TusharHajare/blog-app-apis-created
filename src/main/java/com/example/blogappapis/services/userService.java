package com.example.blogappapis.services;

import com.example.blogappapis.payloads.UserDto;

import java.util.List;

public interface userService
{

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}