package com.eshop.userservice.dtos;

import com.eshop.userservice.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    public static UserDto of(User user){
        if(user == null) return null;
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}