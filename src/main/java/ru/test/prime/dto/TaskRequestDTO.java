package ru.test.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.test.prime.model.User;
import ru.test.prime.model.enums.TaskStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO implements BaseRequestDTO {
    private String description;
    private User responsible;
    private TaskStatus status;
}
