package com.eshop.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}