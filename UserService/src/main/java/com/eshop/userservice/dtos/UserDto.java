package com.eshop.userservice.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.eshop.userservice.models.User}
 */
@Value
public class UserDto implements Serializable {
    String username;
    String password;
    String email;
    String phone;
    String firstName;
    String lastName;
}