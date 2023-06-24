package ru.itis.javalab.dto.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Страница с отзывами")
public class FeedbacksPage {
    @Schema(description = "Список отзывов")
    private List<FeedbackDto> feedbacks;

    @Schema(description = "Общее количество страниц")
    private Integer totalPagesCount;
}
