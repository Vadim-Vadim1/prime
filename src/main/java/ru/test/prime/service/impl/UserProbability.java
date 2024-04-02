package ru.test.prime.service.impl;

import lombok.Getter;
import lombok.Setter;
import ru.test.prime.model.User;

@Getter
public class UserProbability {
    @Setter
    private User user;
    private double probability;
    private long countTask;

    public UserProbability(User user, long countTask) {
        this.user = user;
        this.countTask = countTask;
        this.probability = calcProbability(countTask);
    }

    public void setCountTask(long countTask) {
        this.countTask = countTask;
        this.probability = calcProbability(countTask);
    }

    private double calcProbability(long countTask) {
        return 1.0 / (countTask + 1.0);
    }

}
