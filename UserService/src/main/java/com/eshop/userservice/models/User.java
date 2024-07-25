package com.eshop.userservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
@JsonDeserialize
public class User extends BaseModel {
    @Column(unique = true, nullable = false)
    private String username;
    private String hashedPassword;

    @Column(unique = true)
    private String email;
    private boolean emailVerified;

    @Column(unique = true)
    private String phone;
    private boolean phoneVerified;

    private String firstName;
    private String lastName;
    private Date birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // this adds column in address table
    @JsonManagedReference
    private List<Address> addresses;

    @OneToOne
    private Address primaryAddress;

    @Override
    public String toString() {
        String rolesString = roles.stream()
                .map(Role::toString)
                .collect(Collectors.joining(", "));

        String addressesString = addresses.stream()
                .map(Address::toString)
                .collect(Collectors.joining(", "));

        return "User{" +
                "username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", phone='" + phone + '\'' +
                ", phoneVerified=" + phoneVerified +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", roles=[" + rolesString + "]" +
                ", addresses=[" + addressesString + "]" +
                ", primaryAddress=" + (primaryAddress != null ? primaryAddress.toString() : "null") +
                '}';
    }}
