package ru.itis.javalab.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;

    private String cardNumber;

    private String ownerName;

    private Integer CVV;
}
