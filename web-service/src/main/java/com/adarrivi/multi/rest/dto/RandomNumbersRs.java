package com.adarrivi.multi.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class RandomNumbersRs {

    private ResponseStatus status;
    private String errorMessage;

    private List<Integer> randomNumbers = new ArrayList<>();

    private RandomNumbersRs(ResponseStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static RandomNumbersRs createOkResponse(List<Integer> randomNumbers) {
        RandomNumbersRs response = new RandomNumbersRs(ResponseStatus.OK, null);
        response.randomNumbers = randomNumbers;
        return response;
    }

    public static RandomNumbersRs createErrorResponse(String errorMessage) {
        return new RandomNumbersRs(ResponseStatus.ERR, errorMessage);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }
}
