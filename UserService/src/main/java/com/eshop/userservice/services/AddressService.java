package com.eshop.userservice.services;

import com.eshop.userservice.models.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address addAddress(Address address);
    Address updateAddress(Address address);

}
