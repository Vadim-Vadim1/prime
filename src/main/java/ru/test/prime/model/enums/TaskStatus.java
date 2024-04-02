package ru.test.prime.model.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    OPEN("Open"),
    COMPLETED("Completed"),
    NOT_COMPLETED("Not completed");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

}
