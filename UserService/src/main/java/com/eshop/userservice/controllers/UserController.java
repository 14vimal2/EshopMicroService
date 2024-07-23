package com.eshop.userservice.controllers;

import com.eshop.userservice.dtos.LoginRequestDto;
import com.eshop.userservice.dtos.LogoutRequestDto;
import com.eshop.userservice.dtos.SignUpDto;
import com.eshop.userservice.dtos.UserDto;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.Token;
import com.eshop.userservice.models.User;
import com.eshop.userservice.repositories.TokenRepository;
import com.eshop.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserById(@PathVariable("userName") String userName) {
        return ResponseEntity.ok(userService.findByUsername(userName));
    }

    @GetMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto) throws Exception {
        if(signUpDto.getUsername() == null || signUpDto.getPassword() == null || (signUpDto.getEmail() == null && signUpDto.getPhone() == null) ) {
            throw new  Exception("username and password cannot be null and either email or phone cannot be empty");
        }
        String username = signUpDto.getUsername();
        String password = signUpDto.getPassword();
        return new ResponseEntity<>(userService.createUser(username, password), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public Token logIn(@RequestBody LoginRequestDto loginRequestDto) {
        if (loginRequestDto.getUsername() == null || loginRequestDto.getPassword() == null) {
            throw new  InvalidParameterException("email and password cannot be null");
        }

        return userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
    }

    @GetMapping("/logout")
    public ResponseEntity<Token> logOut(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logout(logoutRequestDto.getToken().getValue());
        return new ResponseEntity<>(logoutRequestDto.getToken(), HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {
        return null;
    }



    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }


}
