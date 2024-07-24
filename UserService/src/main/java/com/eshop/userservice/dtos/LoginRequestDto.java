package com.eshop.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String username;
    private String password;

    @Override
    public String toString() {
        return username + ":" + password;
    }
}
