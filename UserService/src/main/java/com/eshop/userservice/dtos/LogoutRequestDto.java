package com.eshop.userservice.dtos;

import com.eshop.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    private Token token;
}
