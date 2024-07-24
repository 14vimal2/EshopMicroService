package com.eshop.userservice.services;

import com.eshop.userservice.dtos.UserDto;
import com.eshop.userservice.exceptions.AddressNotFoundException;
import com.eshop.userservice.exceptions.UserAlreadyExistsException;
import com.eshop.userservice.exceptions.UserNotFoundException;
import com.eshop.userservice.models.Address;
import com.eshop.userservice.models.Token;
import com.eshop.userservice.models.User;
import com.eshop.userservice.repositories.AddressRepository;
import com.eshop.userservice.repositories.TokenRepository;
import com.eshop.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AddressRepository addressRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            TokenRepository tokenRepository
    ) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Token login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        User user = userOptional.get();
        String hashedPassword = user.getHashedPassword();

        if (!bCryptPasswordEncoder.matches(password, hashedPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }

        Token token = new Token();


        token.setUser(user);
        // Get today's date
        LocalDate today = LocalDate.now();

        // Add 30 days to today's date
        LocalDate dateAfter30Days = today.plusDays(30);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(dateAfter30Days.atStartOfDay(ZoneId.systemDefault()).toInstant());

        token.setExpiryDate(expiryDate);

        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        return tokenRepository.save(token);

    }

    @Override
    public void logout(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValue(token);

        if (tokenOptional.isPresent()) {
            Token savedToken = tokenOptional.get();
            savedToken.setDeleted(true);
            tokenRepository.save(savedToken);
        } else {
            throw new RuntimeException("Token not found");
        }
    }

    @Override
    public UserDto validateToken(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByValue(token);

        if(tokenOptional.isPresent() && tokenOptional.get().getExpiryDate().after(new Date())) {
            return UserDto.of(tokenOptional.get().getUser());
        }
        return null;
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
    public User findByPhone(String phone) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with phone: " + phone);
    }


    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();

            if(user.getUsername() != null){
                userToUpdate.setUsername(user.getUsername());
            }

            if(user.getHashedPassword() != null){
                userToUpdate.setHashedPassword(user.getHashedPassword());
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
