package ru.itis.javalab.dto.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Новый отзыв")
public class NewFeedbackDto {
    @Schema(description = "текст отзыва", example = "Работа выполнена хорошо")
    private String text;

    @Schema(description = "оценка пользователя по 5 бальной шкале")
    private Integer score;

}
