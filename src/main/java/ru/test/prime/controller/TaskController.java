package ru.test.prime.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.test.prime.dto.TaskRequestDTO;
import ru.test.prime.dto.TaskResponseDTO;
import ru.test.prime.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Page<TaskResponseDTO> getAllTask(Pageable pageable) {
        return taskService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO geTaskById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long createTask(@RequestBody TaskRequestDTO user) {
        return taskService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO user) {
        taskService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}
