package com.eshop.userservice.services;

import com.eshop.userservice.exceptions.AddressNotFoundException;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.Address;
import com.eshop.userservice.models.User;
import com.eshop.userservice.repositories.AddressRepository;
import com.eshop.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(String username, String password) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with email: " + email);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with username: " + username);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with id: " + id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("Invalid username or password");
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("Invalid email or password");
    }

    @Override
    public User findByPhone(String phone) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with phone: " + phone);
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        Optional<User> userOptional = userRepository.findByPhoneAndPassword(phone, password);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("Invalid phone or password");
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();

            if(user.getUsername() != null){
                userToUpdate.setUsername(user.getUsername());
            }

            if(user.getPassword() != null){
                userToUpdate.setPassword(user.getPassword());
            }

            if(user.getEmail() != null){
                userToUpdate.setEmail(user.getEmail());
            }

            if(user.getPhone() != null){
                userToUpdate.setPhone(user.getPhone());
            }

            if(user.getFirstName() != null) {
                userToUpdate.setFirstName(user.getFirstName());
            }

            if(user.getLastName() != null) {
                userToUpdate.setLastName(user.getLastName());
            }

            if(user.getBirthday() != null) {
                userToUpdate.setBirthday(user.getBirthday());
            }

            if(user.getPrimaryAddress() != null) {
                userToUpdate.setPrimaryAddress(user.getPrimaryAddress());
            }

            return userRepository.save(userToUpdate);
        }
        throw new UserNotFoundException("User not found with id: " + user.getId());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Address addUserAddress(User user, Address address) {
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address updateUserAddress(User user, Address address) {
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address updateUserPrimaryAddress(User user, Long addressId) throws AddressNotFoundException {
        Optional<Address> addressOptional = addressRepository.findById(addressId);

        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            user.setPrimaryAddress(address);
            address.setUser(userRepository.save(user));
            return address;
        }

        throw new AddressNotFoundException("Address not found with id: " + addressId);
    }
}
