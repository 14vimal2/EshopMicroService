package com.eshop.productservice.services;

import com.eshop.productservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService {
    private RestTemplate restTemplate;


    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        ResponseEntity<UserDto> responseEntity = restTemplate.
                postForEntity(
                        "http://localhost:8181/users/validate/" + token,
                        null,
                        UserDto.class
                );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return null;
    }
}
