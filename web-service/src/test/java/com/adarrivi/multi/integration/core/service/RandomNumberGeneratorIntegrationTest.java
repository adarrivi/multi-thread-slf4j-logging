package com.adarrivi.multi.integration.core.service;

import com.adarrivi.multi.core.service.AsyncRandomNumberGenerator;
import com.adarrivi.multi.integration.GenericIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RandomNumberGeneratorIntegrationTest extends GenericIntegrationTest {

    @Autowired
    private AsyncRandomNumberGenerator victim;

    private List<Integer> outputNumbers;

    @Test
    public void generateNumbers_Generates10Numbers() {
        whenGenerateNumbers();
        thenNumberListSizeShouldBe(10);
    }

    private void whenGenerateNumbers() {
        try {
            outputNumbers = victim.generateRandomNumbers().get();
        } catch (ExecutionException | InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

    private void thenNumberListSizeShouldBe(int expectedSize) {
        Assert.assertEquals(expectedSize, outputNumbers.size());
    }

}
