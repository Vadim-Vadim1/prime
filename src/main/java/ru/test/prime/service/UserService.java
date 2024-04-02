package ru.test.prime.service;

import ru.test.prime.dto.UserRequestDTO;
import ru.test.prime.dto.UserResponseDTO;
import ru.test.prime.model.User;

public interface UserService extends BaseService<User, Long, UserRequestDTO, UserResponseDTO> {
}
