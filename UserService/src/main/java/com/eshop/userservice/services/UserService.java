package com.eshop.userservice.services;

import com.eshop.userservice.exceptions.AddressNotFoundException;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.Address;
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

    User createUser(String username, String password) throws UserAlreadyExistsException;
    User findByEmail(String email) throws UserNotFoundException;
    User findByUsername(String username);
    User findById(Long id);
    User findByUsernameAndPassword(String username, String password);
    User findByEmailAndPassword(String email, String password);
    User findByPhone(String phone);
    User findByPhoneAndPassword(String phone, String password);
    User updateUser(User user);
    void deleteUser(Long id);

    Address addUserAddress(User user, Address address);
    Address updateUserAddress(User user, Address address);
    Address updateUserPrimaryAddress(User user, Long addressId) throws AddressNotFoundException;

}
