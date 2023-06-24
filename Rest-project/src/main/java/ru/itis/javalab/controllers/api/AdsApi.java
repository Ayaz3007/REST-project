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
import ru.itis.javalab.dto.ads.AdDto;
import ru.itis.javalab.dto.ads.AdsPage;
import ru.itis.javalab.dto.ads.NewAdDto;
import ru.itis.javalab.dto.ads.UpdateAdDto;

@Tags(value = {@Tag(name = "Ads")})
@RequestMapping("/ads")
public interface AdsApi {

    @Operation(summary = "Получение списка объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с объявлениями",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdsPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<AdsPage> getAllAds(@Parameter(description = "Номер страницы") @RequestParam("page") int page);

    @Operation(summary = "Добавление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленное объявление",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<AdDto> addAd(@RequestBody NewAdDto newAdDto);

    @Operation(summary = "Получение объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрошенное объявление",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @GetMapping("/{ad-id}")
    ResponseEntity<AdDto> getAd(@Parameter(description = "Идентификатор объявления", example = "1")
                                @PathVariable("ad-id") Long adId);

    @Operation(summary = "Обновление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленное объявление",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @PutMapping("/{ad-id}")
    ResponseEntity<AdDto> updateAd(@Parameter(description = "Идентификатор объявления", example = "1")
                                   @PathVariable("ad-id") Long adId, @RequestBody UpdateAdDto updateAdDto);

    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Объявление удалено"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @DeleteMapping("/{ad-id}")
    ResponseEntity<?> deleteAd(@Parameter(description = "Идентификатор объявления", example = "1")
                               @PathVariable("ad-id") Long adId);

    @Operation(summary = "Публикация объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Объявление опубликовано"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @PutMapping("/{ad-id}/publish")
    ResponseEntity<AdDto> publishAd(@Parameter(description = "Идентификатор объявления", example = "1")
                                    @PathVariable("ad-id") Long adId);


    @Operation(summary = "Поиск объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с объявлениями")
    })
    @GetMapping("/search")
    ResponseEntity<AdsPage> searchAd(@Parameter(description = "Запрос", example = "Таскать")
                                   @RequestParam("query") String query,
                                   @Parameter(description = "Номер страницы") @RequestParam("page") int page,
                                     @Parameter(description = "Категория объявлений")
                                     @RequestParam(value = "category", required = false) String category);

    @Operation(summary = "Получение списка объявлений определенной катгории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с объявлениями",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdsPage.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    @GetMapping("/categories")
    ResponseEntity<AdsPage> getAdsByCategory(@Parameter(description = "Номер страницы") @RequestParam("page") int page,
                                             @Parameter(description = "Категория объявления")
                                             @RequestParam("category") String category);
}
