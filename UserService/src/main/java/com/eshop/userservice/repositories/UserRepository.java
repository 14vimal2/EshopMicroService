package com.eshop.userservice.repositories;

import com.eshop.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndHashedPassword(String email, String password);
    Optional<User> findByUsernameAndHashedPassword(String username, String password);
    Optional<User> findByPhone(String phone);
    Optional<User> findByPhoneAndHashedPassword(String phone, String password);





}
