package com.adarrivi.multi.core.service.impl;

import com.adarrivi.multi.core.service.RandomNumberGenerator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
class AsyncRandomNumberGeneratorImpl implements RandomNumberGenerator {

    private static final int LIST_SIZE = 10;

    private Random randomGenerator = new Random();


    AsyncRandomNumberGeneratorImpl() {
        //To limit scope
    }

    @Override
    @Async
    public List<Integer> generateRandomNumbers() {
        return randomGenerator.ints(0, 100).boxed().limit(LIST_SIZE).collect(Collectors.toList());
    }
}
