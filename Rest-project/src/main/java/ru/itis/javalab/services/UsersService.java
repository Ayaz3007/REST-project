package ru.itis.javalab.services;

import ru.itis.javalab.dto.ads.AdDto;
import ru.itis.javalab.dto.ads.AdsPage;
import ru.itis.javalab.dto.users.SignUpDto;
import ru.itis.javalab.dto.users.UpdateUserDto;
import ru.itis.javalab.dto.users.UserDto;
import ru.itis.javalab.dto.users.UsersPage;

import java.util.List;

public interface UsersService {
    UsersPage getAllUsers(int page);
    UserDto addUser(SignUpDto signUpDto);

    UserDto getUser(Long userId);

    UserDto updateUser(Long userId, UpdateUserDto updateUser);

    void deleteUser(Long userId);

    UserDto publishUser(Long userId);

    void addFavouriteAd(Long userId, Long AdId);

    void deleteAdFromFavourites(Long userId, Long adId);

    AdsPage getFavoriteAdsForUser(Long userId, int page);
}
