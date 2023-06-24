package ru.itis.javalab.security.utils;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtUtil {
        Map<String, String> generateTokens(String subject, String authority, String issuer);

        Authentication buildAuthentication(String token) throws JWTVerificationException;
}

