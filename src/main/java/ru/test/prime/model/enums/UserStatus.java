package ru.test.prime.model.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    ONLINE("Online"),
    OFFLINE("Offline");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
