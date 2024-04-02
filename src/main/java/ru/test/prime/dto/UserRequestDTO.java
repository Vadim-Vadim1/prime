package ru.test.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.test.prime.model.enums.UserStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO implements BaseRequestDTO {
    private String login;
    private String fullName;
    private UserStatus userStatus;
}
