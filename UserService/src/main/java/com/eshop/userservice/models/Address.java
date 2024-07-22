package com.eshop.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseModel {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
