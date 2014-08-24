package com.adarrivi.multi.core.service.impl;

import com.adarrivi.multi.core.service.AsyncRandomNumberGenerator;
import com.adarrivi.multi.core.service.RandomNumberService;
import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
class FaultyRandomNumberServiceImpl implements RandomNumberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaultyRandomNumberServiceImpl.class);

    @Autowired
    private AsyncRandomNumberGenerator randomNumberGenerator;

    private Random randomGenerator = new Random();


    @Override
    public RandomNumbersRs generateRandomNumbers(RandomNumbersRq request) {
        if (isRandomNetworkError()) {
            LOGGER.error("Error in the network found!");
            return RandomNumbersRs.createErrorResponse("Sorry, network error!");
        }
        LOGGER.debug("Requesting random number to async generator...");
        Future<List<Integer>> listFuture = randomNumberGenerator.generateRandomNumbers();
        try {
            return RandomNumbersRs.createOkResponse(listFuture.get(200, TimeUnit.MILLISECONDS));
        } catch (ExecutionException | InterruptedException e) {
            return RandomNumbersRs.createErrorResponse(e.getMessage());
        } catch (TimeoutException e) {
            //TimeoutException is just a timer warning, doesn't interrupt the running thread
            listFuture.cancel(true);
            return RandomNumbersRs.createErrorResponse(e.getMessage());
        }
    }

    private boolean isRandomNetworkError() {
        return randomGenerator.nextInt(10) < 2;
    }
}
