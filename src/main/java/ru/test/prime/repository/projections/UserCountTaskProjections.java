package ru.test.prime.repository.projections;

import ru.test.prime.model.User;

public interface UserCountTaskProjections {
    User getUser();
    Long getTaskCount();
}
