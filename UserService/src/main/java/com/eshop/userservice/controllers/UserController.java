package com.eshop.userservice.controllers;

import com.eshop.userservice.dtos.UserDto;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.User;
import com.eshop.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        return new ResponseEntity<>(userService.createUser(username, password), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }


}
