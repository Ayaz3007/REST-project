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
import ru.itis.javalab.dto.ExceptionDto;
import ru.itis.javalab.dto.cheques.NewChequeDto;
import ru.itis.javalab.dto.cheques.ChequeDto;
import ru.itis.javalab.dto.cheques.ChequePageDto;

@Tags(value = {
        @Tag(name = "Cheques")
})
@RequestMapping("/cheques")
public interface ChequesApi {

    @GetMapping("/{cheque-id}")
    @Operation(summary = "Получение чека по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о чеке.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChequeDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    ResponseEntity<ChequeDto> getCheque(@Parameter(description = "Идентификатор чека")
                                               @PathVariable("cheque-id") Long id);


    @GetMapping
    @Operation(summary = "Получение определенной страницы чеков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с чеками.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ChequePageDto.class))
                    })
    })
    ResponseEntity<ChequePageDto> getPage(@Parameter(description = "Номер страницы")
                                                 @RequestParam("page") int numberOfPage);

    @PostMapping
    @Operation(summary = "Добавление чека", description = "Добавление чека")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный чек",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NewChequeDto.class))
                    })
    })
    ResponseEntity<ChequeDto> addCheque(@Parameter(description = "Новый чек")
                                                   @RequestBody NewChequeDto newChequeDto);


    @PutMapping("/{cheque-id}")
    @Operation(summary = "Изменение чека по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный чек",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NewChequeDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    })
    })
    ResponseEntity<ChequeDto> updateCheque(@Parameter(description = "Идентификатор чека")
                                                      @PathVariable("cheque-id") Long orderId,
                                                  @Parameter(description = "Обновленный чек") @RequestBody
                                                          NewChequeDto newChequeDto);


    @Operation(summary = "Удаление чека по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Чек удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{cheque-id}")
    ResponseEntity<?> deleteCheque(@Parameter(description = "Идентификатор чека") @PathVariable("cheque-id")Long id);
    @Operation(summary = "Получение страницы чеков конкретного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чеки получены"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/users/{user-id}")
    ResponseEntity<ChequePageDto> getChequesForUser(@Parameter(description = "Идентификатор пользователя")
                                                           @PathVariable("user-id")Long id,
                                                           @RequestParam("page") int page);
}
