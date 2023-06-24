package ru.itis.javalab.dto.cheques;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.Cheque;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChequeDto {
    @Schema(description = "Идентификатор чека.", example = "1")
    private Long id;

    @Schema(description = "Цена в чеке.", example = "1232.0")
    private double price;

    @Schema(description = "Идентификатор пользователя, который откликнулся на данное объявление.", example = "1")
    private Long executorId;

    @Schema(description = "Идентификатор объявления.", example = "1")
    private Long advertisementId;

    @Schema(description = "Время, в которое была совершена оплата.", example = "2023-02-28 09:43:44.000000")
    private Date paymentTime;

    public static ChequeDto from(Cheque cheque) {
        return ChequeDto.builder()
                .id(cheque.getId())
                .advertisementId(cheque.getAdvertisement().getId())
                .executorId(cheque.getExecutor().getId())
                .price(cheque.getPrice())
                .paymentTime(cheque.getPaymentTime())
                .build();
    }

    public static List<ChequeDto> from(List<Cheque> cheques) {
        return cheques.stream().map(ChequeDto::from).collect(Collectors.toList());
    }

}
