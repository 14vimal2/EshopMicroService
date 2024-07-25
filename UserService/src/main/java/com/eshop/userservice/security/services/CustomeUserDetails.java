package com.eshop.userservice.security.services;

import com.eshop.userservice.models.Role;
import com.eshop.userservice.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@JsonDeserialize
public class CustomeUserDetails implements UserDetails {
    private String password;
    private String username;
    private String email;
    private String phone;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities;



    public CustomeUserDetails() {}

    public CustomeUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getHashedPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            System.out.println( "ye line print nahi hua " +  role.getName());
            this.authorities.add(new CustomeGrantedAuthority(role));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("dekh bhadwe ye hai authorities " + authorities );
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


}
