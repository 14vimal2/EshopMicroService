package com.eshop.userservice.services;

import com.eshop.userservice.dtos.UserDto;
import com.eshop.userservice.exceptions.AddressNotFoundException;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.Address;
import com.eshop.userservice.models.Token;
import com.eshop.userservice.models.User;

import java.util.List;

public interface UserService {
    /*
    * 1. Create User
    * 2. Add address to user
    * 3. Save primary address
    * 4. SaveUserDetails
    * */

    List<User> findAll();

    User createUser(String username, String password, String email) throws UserAlreadyExistsException;
    User findByEmail(String email) throws UserNotFoundException;
    User findByUsername(String username);
    User findById(Long id);
    User findByPhone(String phone);
    User updateUser(User user);
    void deleteUser(Long id);

    Address addUserAddress(User user, Address address);
    Address updateUserAddress(User user, Address address);
    Address updateUserPrimaryAddress(User user, Long addressId) throws AddressNotFoundException;

    Token login(String username, String password);
    void logout(String token);
    UserDto validateToken(String token);
}
