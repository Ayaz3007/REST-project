package ru.itis.javalab.dto.ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Страница с объявлениями")
public class AdsPage {
    @Schema(description = "Объявления")
    private List<AdDto> ads;
    @Schema(description = "Общее количество страниц")
    private Integer totalPagesCount;
}
