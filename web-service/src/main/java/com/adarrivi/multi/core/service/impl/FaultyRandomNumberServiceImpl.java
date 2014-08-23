package com.adarrivi.multi.core.service.impl;

import com.adarrivi.multi.core.service.RandomNumberGenerator;
import com.adarrivi.multi.core.service.RandomNumberService;
import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class FaultyRandomNumberServiceImpl implements RandomNumberService {

    @Autowired
    private RandomNumberGenerator randomNumberGenerator;

    private Random randomGenerator = new Random();


    @Override
    public RandomNumbersRs generateRandomNumbers(RandomNumbersRq request) {
        if (isRandomNetworkError()) {
            return RandomNumbersRs.createErrorResponse("Sorry, network error!");
        }
        return RandomNumbersRs.createOkResponse(randomNumberGenerator.generateRandomNumbers());
    }

    private boolean isRandomNetworkError() {
        return randomGenerator.nextInt(10) < 2;
    }
}
