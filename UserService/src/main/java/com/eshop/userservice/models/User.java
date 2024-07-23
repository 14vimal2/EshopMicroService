package com.eshop.userservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // this adds column in address table
    @JsonManagedReference
    private List<Address> addresses;

    @OneToOne
    private Address primaryAddress;
}
