package ru.itis.javalab.dto.cheques;

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
@Schema(description = "Страница с чеками.")
public class ChequePageDto {
    @Schema(description = "Список чеков.")
    private List<ChequeDto> cheques;

    @Schema(description = "Общее количество страниц.")
    private Integer totalPageCount;
}
