package ru.itis.javalab.dto.ads;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import ru.itis.javalab.dto.users.UserDto;
import ru.itis.javalab.models.Ad;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Объявление")
public class AdDto {
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Заголовок объявления", example = "Перетаскать кирпичи")
    private String header;

    @Schema(description = "Описание объявления")
    private String description;

    @Schema(description = "Цена за выполнение")
    private Integer price;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Исполнитель")
    private UserDto performer;

    public static AdDto from(Ad ad) {

        return AdDto.builder()
                .id(ad.getId())
                .description(ad.getDescription())
                .header(ad.getHeader())
                .price(ad.getPrice())
                .address(ad.getAddress())
                .performer(ad.getPerformer() == null ? null : UserDto.from(ad.getPerformer()))
                .build();
    }

    public static List<AdDto> from(List<Ad> ads) {
        return ads.stream()
                .map(AdDto::from)
                .collect(Collectors.toList());
    }
}
