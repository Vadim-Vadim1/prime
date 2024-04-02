package ru.test.prime.mapper;

import org.mapstruct.Mapper;
import ru.test.prime.dto.TaskRequestDTO;
import ru.test.prime.dto.TaskResponseDTO;
import ru.test.prime.model.Task;


@Mapper(componentModel = "spring")
public interface RequestMapper extends BaseMapper<Task, TaskRequestDTO, TaskResponseDTO> {
}
