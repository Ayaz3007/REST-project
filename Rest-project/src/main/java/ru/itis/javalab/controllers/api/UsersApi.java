package ru.itis.javalab.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.*;
import ru.itis.javalab.dto.card.CardDto;
import ru.itis.javalab.dto.card.NewCardDto;
import ru.itis.javalab.dto.feedback.FeedbackDto;
import ru.itis.javalab.dto.feedback.FeedbacksPage;
import ru.itis.javalab.dto.feedback.NewFeedbackDto;
import ru.itis.javalab.dto.users.SignUpDto;
import ru.itis.javalab.dto.users.UpdateUserDto;
import ru.itis.javalab.dto.users.UserDto;
import ru.itis.javalab.dto.users.UsersPage;
import ru.itis.javalab.dto.ads.AdsPage;

@Tags(value = {
        @Tag(name = "Users")
})
public interface UsersApi {
    @Operation(summary = "Получение пользователей на странице")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей на странице",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsersPage.class))
                    }
            )
    })
    @GetMapping
    ResponseEntity<UsersPage> getAllUsers(@RequestParam("page") int page);

    @Operation(summary = "Добавление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный пользователь",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<UserDto> addUser(@RequestBody SignUpDto signUpDto);

    @Operation(summary = "Получение пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о пользователе",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getById(@Parameter(description = "id пользователя", example = "5")
                                    @PathVariable("user-id") Long userId);

    @Operation(summary = "Обновление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный пользователь",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{user-id}")
    ResponseEntity<UserDto> updateById(@Parameter(description = "id пользователя", example = "5")
                                       @PathVariable("user-id") Long userId, @RequestBody UpdateUserDto updateUser);

    @Operation(summary = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Пользователь удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{user-id}")
    ResponseEntity<?> deleteUser(@Parameter(description = "идентификатор пользователя", example = "8")
                                 @PathVariable("user-id") Long userId);

    @Operation(summary = "Публикация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Опубликованный пользователь"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{user-id}/confirm")
    ResponseEntity<UserDto> publishUser(@Parameter(description = "идентификатор пользователя", example = "8")
                                        @PathVariable("user-id") Long userId);

    @Operation(summary = "Добавление отзыва пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный отзыв",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FeedbackDto.class))
                    }
            )
    })
    @PostMapping("/{user-id}/feedbacks")
    ResponseEntity<FeedbackDto> addFeedback(@RequestBody NewFeedbackDto feedbackDto,
                                            @PathVariable("user-id") Long userId);

    @Operation(summary = "Удаление отзыва на пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Отзыв удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{user-id}/feedbacks/{feedback-id}")
    ResponseEntity<?> deleteFeedback(@Parameter(description = "Идентификатор пользователя", example = "1")
                                               @PathVariable("user-id") Long userId,
                                               @Parameter(description = "Идентификатор отзыва", example = "1")
                                               @PathVariable("feedback-id") Long feedbackId);

    @Operation(summary = "Обновление отзыва на пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный отзыв",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FeedbackDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{user-id}/feedbacks/{feedback-id}")
    ResponseEntity<FeedbackDto> updateFeedback(@RequestBody NewFeedbackDto feedbackDto,
                                               @Parameter(description = "Идентификатор пользователя", example = "1")
                                               @PathVariable("user-id") Long userId,
                                               @Parameter(description = "Идентификатор отзыва", example = "1")
                                               @PathVariable("feedback-id") Long feedbackId);

    @Operation(summary = "Получение отзывов пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отзывов пользователя",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FeedbacksPage.class))
                    }
            )
    })
    @GetMapping("/{user-id}/feedbacks")
    ResponseEntity<FeedbacksPage> getAllUsersFeedbacks(@RequestParam("page") int page,
                                                       @PathVariable("user-id") Long userId);


    @Operation(summary = "Добавление карты пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Карта успешно добавлена",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NewCardDto.class))
                    }
            )
    })
    @PostMapping("/{user-id}/card")
    ResponseEntity<?> addCard(@RequestBody NewCardDto newCardDto,
                                            @PathVariable("user-id") Long userId);

    @Operation(summary = "Удаление карты у пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Карта удалена"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{user-id}/card")
    ResponseEntity<?> deleteCard(@Parameter(description = "Идентификатор пользователя", example = "1")
                                     @PathVariable("user-id") Long userId);

    @Operation(summary = "Получение карты пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные о карте",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CardDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{user-id}/card")
    ResponseEntity<CardDto> getCardByUserId(@Parameter(description = "id пользователя", example = "5")
                                    @PathVariable("user-id") Long userId);

    @Operation(summary = "Добавление избранного объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление добавлено")
    })
    @PostMapping("/{user-id}/favorites/{ad-id}")
    ResponseEntity<?> addFavouriteAd(@PathVariable("user-id") Long userId,
                                     @PathVariable("ad-id") Long adId);

    @Operation(summary = "Удаление объявления из избранного")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Объявление убрано из избранных"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{user-id}/favorites/{ad-id}")
    ResponseEntity<?> deleteAdFromFavorites(@Parameter(description = "Идентификатор пользователя", example = "1")
                                            @PathVariable("user-id") Long userId,
                                            @Parameter(description = "Идентификатор объявления", example = "1")
                                            @PathVariable("ad-id") Long adId);

    @Operation(summary = "Получение объявлений из избранных пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список объвлений из избранного",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdsPage.class))
                    }
            )
    })
    @GetMapping("/{user-id}/favorites")
    ResponseEntity<AdsPage> getAllAdsFromFavorites(@RequestParam("page") int page,
                                                   @PathVariable("user-id") Long userId);


}
