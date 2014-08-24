package com.adarrivi.multi.rest.controller;

import com.adarrivi.multi.core.service.RandomNumberService;
import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RandomNumberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomNumberController.class);

    @Autowired
    private RandomNumberService randomNumberService;

    RandomNumberController() {
        //To limit scope
    }

    @RequestMapping(value = "/randomNumbers", method = RequestMethod.GET)
    public RandomNumbersRs randomNumbers(@RequestBody RandomNumbersRq request) {
        LOGGER.debug("Received request with id: {}", request.getRqId());
        return randomNumberService.generateRandomNumbers(request);
    }


}
