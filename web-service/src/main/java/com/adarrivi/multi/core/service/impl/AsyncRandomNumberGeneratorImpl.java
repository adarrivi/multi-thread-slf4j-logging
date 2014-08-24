package com.adarrivi.multi.core.service.impl;

import com.adarrivi.multi.core.service.AsyncRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
class AsyncRandomNumberGeneratorImpl implements AsyncRandomNumberGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRandomNumberGeneratorImpl.class);
    private static final int LIST_SIZE = 10;

    private Random randomGenerator = new Random();


    AsyncRandomNumberGeneratorImpl() {
        //To limit scope
    }

    @Override
    @Async
    public Future<List<Integer>> generateRandomNumbers() {
        LOGGER.debug("Generating random numbers...");
        return new AsyncResult<>(randomGenerator.ints(0, 100).boxed().limit(LIST_SIZE).collect(Collectors.toList()));
    }
}
