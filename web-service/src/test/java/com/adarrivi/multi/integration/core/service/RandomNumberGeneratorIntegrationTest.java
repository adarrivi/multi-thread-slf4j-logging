package com.adarrivi.multi.integration.core.service;

import com.adarrivi.multi.core.service.RandomNumberGenerator;
import com.adarrivi.multi.integration.GenericIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RandomNumberGeneratorIntegrationTest extends GenericIntegrationTest {

    @Autowired
    private RandomNumberGenerator victim;

    private List<Integer> outputNumbers;

    @Test
    public void generateNumbers_Generates10Numbers() {
        whenGenerateNumbers();
        thenNumberListSizeShouldBe(10);
    }

    private void whenGenerateNumbers() {
        outputNumbers = victim.generateRandomNumbers();
    }

    private void thenNumberListSizeShouldBe(int expectedSize) {
        Assert.assertEquals(expectedSize, outputNumbers.size());
    }

}
