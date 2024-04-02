package ru.test.prime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.prime.dto.TaskRequestDTO;
import ru.test.prime.dto.TaskResponseDTO;
import ru.test.prime.mapper.BaseMapper;
import ru.test.prime.model.Task;
import ru.test.prime.repository.TaskRepository;
import ru.test.prime.service.BaseServiceImpl;
import ru.test.prime.service.TaskService;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TaskServiceImpl extends
        BaseServiceImpl<Task,
                Long,
                TaskRequestDTO,
                TaskResponseDTO,
                TaskRepository>
        implements TaskService {

    @Autowired
    public TaskServiceImpl(TaskRepository repository,
                           BaseMapper<Task, TaskRequestDTO, TaskResponseDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public Page<TaskResponseDTO> getAll(Pageable pageable) {
        return repository.findPageBy(pageable).map(mapper::toResponse);
    }
}
