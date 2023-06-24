package ru.itis.javalab.dto.cheques;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Новый чек.")
public class NewChequeDto {

    @Schema(description = "Общая цена в чеке.", example = "1232.0")
    @Digits(integer = 7, fraction = 2, message = "{newCheque.price.digit}")
    @PositiveOrZero(message = "{newCheque.price.negative}")
    @NotNull(message = "{newCheque.price.null}")
    private double price;

    @Schema(description = "Идентификатор пользователя, который откликнулся на данное объявление.", example = "1")
    private Long executorId;

    @Schema(description = "Идентификатор объявления.", example = "1")
    private Long advertisementId;
}
