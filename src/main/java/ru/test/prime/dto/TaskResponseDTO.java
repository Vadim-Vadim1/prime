package ru.test.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.test.prime.model.enums.TaskStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO implements BaseResponseDTO {
    private Long id;
    private String description;
    private UserRequestDTO responsible;
    private TaskStatus status;
}
