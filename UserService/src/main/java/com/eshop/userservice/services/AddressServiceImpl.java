package com.eshop.userservice.services;

import com.eshop.userservice.models.Address;
import com.eshop.userservice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }


    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }
}
