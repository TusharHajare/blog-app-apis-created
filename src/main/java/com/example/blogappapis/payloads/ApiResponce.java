package com.example.blogappapis.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce
{
    private String message;
    private boolean success;
}
