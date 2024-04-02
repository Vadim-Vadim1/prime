package ru.test.prime.mapper;

import org.mapstruct.Mapper;
import ru.test.prime.dto.UserRequestDTO;
import ru.test.prime.dto.UserResponseDTO;
import ru.test.prime.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserRequestDTO, UserResponseDTO> {
}
