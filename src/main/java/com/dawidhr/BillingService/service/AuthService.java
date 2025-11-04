package com.dawidhr.BillingService.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AuthService {

    public String create(String email) {
       // jwt = JWTCreator.init().withSubject(email).withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC)).sign(Algorithm.HMAC256("test"));
        return JWT.create().withSubject(email).withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC)).sign(Algorithm.HMAC256("test"));
    }
}
