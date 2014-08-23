package com.adarrivi.multi.rest.controller;

import com.adarrivi.multi.core.service.RandomNumberService;
import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RandomNumberController {

    @Autowired
    private RandomNumberService randomNumberService;

    RandomNumberController() {
        //To limit scope
    }

    @RequestMapping(value = "/randomNumbers", method = RequestMethod.GET)
    public RandomNumbersRs randomNumbers(@RequestBody RandomNumbersRq request) {
        return randomNumberService.generateRandomNumbers(request);
    }


}
