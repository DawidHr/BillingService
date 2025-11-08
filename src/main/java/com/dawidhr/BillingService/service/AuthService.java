package com.dawidhr.BillingService.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthService {

    public String create(String email) {
        return JWT.create().withSubject(email).withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC)).sign(Algorithm.HMAC256("test"));
    }


    public String getSubject(String auth) {
        // jwt = JWTCreator.init().withSubject(email).withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC)).sign(Algorithm.HMAC256("test"));
      //  return JWT.create().decodeJwt().withSubject(email).withExpiresAt(LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC)).sign(Algorithm.HMAC256("test"));
        return JWT.decode(auth).getSubject();
    }
}
