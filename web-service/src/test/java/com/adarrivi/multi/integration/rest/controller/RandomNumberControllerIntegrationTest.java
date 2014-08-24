package com.adarrivi.multi.integration.rest.controller;

import com.adarrivi.multi.integration.rest.GenericWebIntegrationTest;
import com.adarrivi.multi.integration.rest.JsonDto;
import com.adarrivi.multi.rest.dto.RandomNumbersRq;
import com.adarrivi.multi.rest.dto.RandomNumbersRs;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class RandomNumberControllerIntegrationTest extends GenericWebIntegrationTest {

    private static final String URL = "/randomNumbers";

    private JsonDto<RandomNumbersRq> jsonRequest;
    private JsonDto<RandomNumbersRs> jsonResponse;

    @Test
    public void randomNumbers_ReturnsOkResponse() {
        givenValidRequest();
        getIntegrationTestHttpRequest().performJsonGet(URL, jsonRequest);
        getIntegrationTestHttpRequest().assertOkResponse();
    }

    private void givenValidRequest() {
        RandomNumbersRq request = new RandomNumbersRq();
        request.setRqId(UUID.randomUUID().toString());
        jsonRequest = new JsonDto<>(request);
    }

    @Test
    public void randomNumbers_ReturnsStatusCode() {
        givenValidRequest();
        getIntegrationTestHttpRequest().performJsonGet(URL, jsonRequest);
        thenResponseShouldContainStatusCode();
    }

    private void thenResponseShouldContainStatusCode() {
        RandomNumbersRs responseAsJsonObject = getIntegrationTestHttpRequest().getResponseAsJsonObject(RandomNumbersRs.class);
        Assert.assertNotNull(responseAsJsonObject.getStatus());
    }

}
