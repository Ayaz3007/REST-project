package ru.itis.javalab.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница с пользователями и общее количество страниц")
public class UsersPage {
    @Schema(description = "список пользователей")
    private List<UserDto> users;

    @Schema(description = "количество страниц", example = "10")
    private Integer totalUsersCount;

}
