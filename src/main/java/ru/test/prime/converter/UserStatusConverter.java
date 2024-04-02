package ru.test.prime.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.test.prime.model.enums.UserStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {
    @Override
    public String convertToDatabaseColumn(UserStatus userStatus) {
        if (userStatus == null) {
            return null;
        }
        return userStatus.getStatus();
    }

    @Override
    public UserStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(UserStatus.values())
                .filter(c -> c.getStatus().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
