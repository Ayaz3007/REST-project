package ru.itis.javalab.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Пользователь")
public class UserDto {
    @Schema(description = "идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "имя пользователя", example = "Вова")
    private String firstName;
    @Schema(description = "фамилия пользователя", example = "Сидоров")
    private String lastName;

    @Schema(description = "почта пользователя", example = "test@mail.ru")
    private String email;

    @Schema(description = "идентификатор карты пользователя", example = "3")
    private Long cardId;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .cardId(user.getCard().getId())
                .build();
    }

    public static  List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
