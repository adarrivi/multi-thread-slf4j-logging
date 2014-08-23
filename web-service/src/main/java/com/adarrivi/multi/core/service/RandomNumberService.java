package com.adarrivi.multi.core.service;

import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;

public interface RandomNumberService {

    RandomNumbersRs generateRandomNumbers(RandomNumbersRq request);
}
