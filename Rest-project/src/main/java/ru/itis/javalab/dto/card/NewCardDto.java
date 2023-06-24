package ru.itis.javalab.dto.card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Карта для оплаты")
public class NewCardDto {
    @Schema(description = "Номер карты", example = "1111111111111111")
    @Size(min = 16, max = 19, message = "{card.number.value")
    @NotNull(message = "{card.number.null}")
    private String cardNumber;

    @Schema(description = "Имя и фамилия владельца карты", example = "PETR IVANOV")
    @Size(min = 3, message = "{card.owner.name.size}")
    @NotNull(message = "{card.owner.name.null}")
    private String ownerName;

    @Schema(description = "CVV-код карты", example = "376")
    @Min(value = 100, message = "{card.cvv.value}")
    @Max(value = 9999, message = "{card.cvv.value}")
    @NotNull(message = "{card.cvv.null}")
    private Integer CVV;
}
