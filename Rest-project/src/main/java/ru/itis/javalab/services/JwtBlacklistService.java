package ru.itis.javalab.services;

public interface JwtBlacklistService {
    void add(String token);

    boolean isExists(String token);
}
