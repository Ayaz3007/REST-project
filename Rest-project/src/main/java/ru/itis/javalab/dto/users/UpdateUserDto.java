package ru.itis.javalab.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Обновленный пользователь")
public class UpdateUserDto {
    @Schema(description = "имя пользователя", example = "Ваня")
    @Size(min = 2, message = "{user.name.size}")
    @NotNull(message = "{user.name.null}")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Иванов")
    @Size(min = 2, message = "{user.surname.size}")
    private String lastName;

    @Schema(description = "почта пользователя", example = "new@mail.ru")
    @NotEmpty(message = "{user.email.notEmpty}")
    @Email(message = "{user.email.pattern}")
    private String email;
}