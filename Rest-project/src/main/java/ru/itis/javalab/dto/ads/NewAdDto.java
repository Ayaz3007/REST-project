package ru.itis.javalab.dto.ads;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Ad;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новое объявление")
public class NewAdDto {

    @Schema(description = "Идентификатор пользователя выставившего объявление")
    private Long userId;

    @Schema(description = "Заголовок объявления")
    @Size(min = 6, max = 32, message = "{newAd.header.size}")
    @NotEmpty(message = "{newAd.header.notEmpty}")
    private String header;

    @Schema(description = "Описание объявления")
    @Size(min = 12, max = 255, message = "{newAd.description.size}")
    @NotEmpty(message = "{newAd.description.notEmpty}")
    private String description;

    @Schema(description = "Цена за выполнение")
    @Digits(integer = 7, fraction = 2, message = "{newAd.price.digit}")
    @PositiveOrZero(message = "newAd.price.negative")
    @NotNull(message = "newAd.price.null")
    private Integer price;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Категория объявления")
    @Size(min = 1, max = 32, message = "{newAd.category.size}")
    @NotEmpty(message = "{newAd.category.notEmpty}")
    private String category;

}
