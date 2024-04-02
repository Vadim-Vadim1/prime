package ru.test.prime.service;

import ru.test.prime.dto.TaskRequestDTO;
import ru.test.prime.dto.TaskResponseDTO;
import ru.test.prime.model.Task;

public interface TaskService extends BaseService<Task, Long, TaskRequestDTO, TaskResponseDTO>{
}
