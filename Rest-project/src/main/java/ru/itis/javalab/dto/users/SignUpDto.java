package ru.itis.javalab.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.validation.constraints.HardPassword;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "Новый пользователь")
public class SignUpDto {
    @Schema(description = "имя пользователя", example = "Ваня")
    @Size(min = 2, message = "{user.name.size}")
    @NotNull(message = "{user.name.null}")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Иванов")
    @Size(min = 2, message = "{user.surname.size}")
    private String lastName;

    @Schema(description = "почта пользователя", example = "new@mail.ru")
    @Email(message = "{user.email.pattern}")
    @NotEmpty(message = "{user.email.notEmpty}")
    private String email;

    @Schema(description = "пароль пользователя", example = "qWerty007+")
    @Size(min = 8, message = "{user.password.size}")
    @NotEmpty(message = "{user.password.notEmpty}")
    @HardPassword(message = "{user.password.easy}")
    private String hashPassword;
}
