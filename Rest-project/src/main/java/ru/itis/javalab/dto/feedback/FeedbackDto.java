package ru.itis.javalab.dto.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Feedback;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Отзыв")
public class FeedbackDto {
    @Schema(description = "Идентификатор отзыва.", example = "1")
    private Long id;

    @Schema(description = "текст отзыва", example = "Работа выполнена хорошо")
    private String text;

    @Schema(description = "оценка пользователя по 5 бальной шкале", example = "4")
    private Integer score;

    @Schema(description = "время, в которое был добавлен пользователь")
    private Date feedbackTime;

    public static FeedbackDto from(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getId())
                .text(feedback.getText())
                .feedbackTime(feedback.getFeedbackTime())
                .score(feedback.getScore())
                .build();
    }

    public static List<FeedbackDto> from(List<Feedback> feedbacks) {
        return feedbacks.stream().map(FeedbackDto::from).collect(Collectors.toList());
    }
}
