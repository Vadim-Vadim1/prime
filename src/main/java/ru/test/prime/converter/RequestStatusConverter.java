package ru.test.prime.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.test.prime.model.enums.TaskStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus taskStatus) {
        if (taskStatus == null) {
            return null;
        }
        return taskStatus.getStatus();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(TaskStatus.values())
                .filter(c -> c.getStatus().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
