package ru.test.prime.service.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGeneratorImpl implements RandomNumberGenerator {
    private final Random random = new Random();

    @Override
    public double generateRandomDouble(double maxValue) {
        return random.nextDouble() * maxValue;
    }
}
