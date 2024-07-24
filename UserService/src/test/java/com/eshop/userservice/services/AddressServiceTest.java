package com.eshop.userservice.services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {



    @Test
    public void someJwtTest() {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String jws = Jwts.builder().subject("Joe").signWith(key).compact();

        String value = Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject(); //.equals("Joe");

        assertEquals("Joe", value );
    }
}