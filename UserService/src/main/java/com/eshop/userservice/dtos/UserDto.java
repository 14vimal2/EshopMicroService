package com.eshop.userservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Getter
@Setter
public class UserDto{
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}