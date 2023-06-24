package ru.itis.javalab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.controllers.api.UsersApi;
import ru.itis.javalab.dto.card.CardDto;
import ru.itis.javalab.dto.card.NewCardDto;
import ru.itis.javalab.dto.feedback.FeedbackDto;
import ru.itis.javalab.dto.feedback.FeedbacksPage;
import ru.itis.javalab.dto.feedback.NewFeedbackDto;
import ru.itis.javalab.dto.users.SignUpDto;
import ru.itis.javalab.dto.users.UpdateUserDto;
import ru.itis.javalab.dto.users.UserDto;
import ru.itis.javalab.dto.users.UsersPage;
import ru.itis.javalab.services.CardsService;
import ru.itis.javalab.services.FeedbacksService;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.dto.ads.AdsPage;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController implements UsersApi {
    private final UsersService userService;
    private final FeedbacksService feedbacksService;

    private final CardsService cardsService;

    @Override
    public ResponseEntity<UsersPage> getAllUsers(int page) {
        return ResponseEntity
                .ok(userService.getAllUsers(page));
    }

    @Override
    public ResponseEntity<UserDto> addUser(@Valid SignUpDto signUpDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addUser(signUpDto));
    }

    @Override
    public ResponseEntity<UserDto> getById(Long userId) {
        return ResponseEntity
                .ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<UserDto> updateById(Long userId, @Valid UpdateUserDto updateUser) {
        return ResponseEntity.accepted()
                .body(userService.updateUser(userId, updateUser));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<UserDto> publishUser(Long userId) {
        return ResponseEntity.accepted()
                .body(userService.publishUser(userId));
    }

    @Override
    public ResponseEntity<?> deleteFeedback(Long userId, Long feedbackId) {
        feedbacksService.delete(userId, feedbackId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<FeedbackDto> updateFeedback(NewFeedbackDto feedbackDto, Long userId, Long feedbackId) {
        return ResponseEntity.accepted()
                .body(feedbacksService.update(feedbackDto, feedbackId, userId));
    }

    @Override
    public ResponseEntity<FeedbackDto> addFeedback(NewFeedbackDto feedbackDto, Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feedbacksService.addFeedback(feedbackDto, userId));
    }

    @Override
    public ResponseEntity<FeedbacksPage> getAllUsersFeedbacks(int page, Long userId) {
        return ResponseEntity
                .ok(feedbacksService.getAllFeedbacks(page, userId));
    }

    @Override
    public ResponseEntity<?> addCard(NewCardDto newCardDto, Long userId) {
        cardsService.addCard(newCardDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<?> deleteCard(Long userId) {
        cardsService.deleteCard(userId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<CardDto> getCardByUserId(Long userId) {
        return ResponseEntity.ok(cardsService.getCardByUser(userId));
    }

    @Override
    public ResponseEntity<?> addFavouriteAd(Long userId, Long adId) {
        userService.addFavouriteAd(userId, adId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<?> deleteAdFromFavorites(Long userId, Long adId) {
        userService.deleteAdFromFavourites(userId, adId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<AdsPage> getAllAdsFromFavorites(int page, Long userId) {
        return ResponseEntity.ok(userService.getFavoriteAdsForUser(userId, page));
    }

}
