package com.example.blogappapis.serviceImpl;

import com.example.blogappapis.entites.User;
import com.example.blogappapis.exceptions.*;
import com.example.blogappapis.payloads.UserDto;
import com.example.blogappapis.repositories.*;
import com.example.blogappapis.services.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements userService
{

    @Autowired
    private userRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = this.dtoToUser(userDto);
        User savedUser = this.repo.save(user);
        return this.userTODto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId)
    {
     User user = this.repo.findById(userId)
             .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

     user.setName(userDto.getName());
     user.setEmail(userDto.getEmail());
     user.setPassword(userDto.getPassword());
     user.setAbout(userDto.getAbout());

     User updateUser = this.repo.save(user);
     UserDto userDto1 = this.userTODto(updateUser);
     return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId)
    {
        User user = this.repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        return this.userTODto(user);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
       List<User> users =  this.repo.findAll();
       List<UserDto> userDtos = users.stream().map(user -> this.userTODto(user)).collect(Collectors.toList());
       return userDtos;
    }

    @Override
    public void deleteUser(Integer userId)
    {
        User user = this.repo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id",userId));
        this.repo.delete(user);
    }

    public User dtoToUser(UserDto userDto)
    {
        User user = this.modelMapper.map(userDto, User.class);

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
    return user;
    }

    public UserDto userTODto(User user)
    {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        user.setAbout(user.getAbout());
    return userDto;
    }


}
