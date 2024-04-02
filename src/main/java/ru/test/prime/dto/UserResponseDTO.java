package ru.test.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.test.prime.model.enums.UserStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponseDTO implements BaseResponseDTO {
    private Long id;
    private String login;
    private String fullName;
    private UserStatus userStatus;
}
