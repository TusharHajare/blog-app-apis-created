package com.example.blogappapis.payloads;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto
{

    private int id;

    @NotEmpty
    @Size(min = 2, message = "Username must be min of 2 characters !!")
    private String name;

    @Email
    private String email;

    @NotEmpty
    @Size(min=4, max = 10, message = "Passsword must be min of 4 and max of 10 chars !!")
    private String password;

    @NotEmpty
    private String about;
}
