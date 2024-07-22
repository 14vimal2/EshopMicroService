package com.eshop.userservice.models;

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
    private String password;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;

    private String firstName;
    private String lastName;
    private Date birthday;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // this adds column in address table
    private List<Address> addresses;

    @OneToOne
    private Address primaryAddress;
}
