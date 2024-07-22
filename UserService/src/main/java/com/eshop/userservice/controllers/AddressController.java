package com.eshop.userservice.controllers;

import com.eshop.userservice.models.Address;
import com.eshop.userservice.services.AddressService;
import com.eshop.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/addresses")
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/{user_name}")
    public ResponseEntity<List<Address>> getAllAddressByUserName(@PathVariable("user_name") String userName) {
        return new ResponseEntity<>(userService.findByUsername(userName).getAddresses() , HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return new ResponseEntity<>( addressService.addAddress(address) , HttpStatus.CREATED);
    }


}
