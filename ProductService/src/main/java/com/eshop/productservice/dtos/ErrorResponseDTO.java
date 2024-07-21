package com.eshop.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
