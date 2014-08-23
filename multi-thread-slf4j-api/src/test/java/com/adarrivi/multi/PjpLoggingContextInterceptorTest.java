package com.adarrivi.multi;

import org.apache.log4j.PatternLayout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This test requires test-log file to be created
 */
public class PjpLoggingContextInterceptorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PjpLoggingContextInterceptorTest.class);


    private InMemoryAppender inMemoryAppender;

    @Before
    public void setUpInMemoryAppender() {
        createInMemoryAppender();
    }

    private void createInMemoryAppender() {
        inMemoryAppender = new InMemoryAppender();
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %5p [%t] [rqId: %X{rqId}] %c{1}:%L - %m%n");
        inMemoryAppender.setLayout(patternLayout);
        inMemoryAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(inMemoryAppender);
    }

    @Test
    public void addIncludedToContext_NoIncludedParam_DoNotAddItToLogging() {
        LOGGER.debug("Hello world");
        thenAllLogLinesShouldContainParamValue(true, "world");
    }

    private void thenAllLogLinesShouldContainParamValue(boolean shouldContain, String value) {
        getAllLogLines().forEach(line -> Assert.assertEquals("Value [" + value + "] found: " + line.contains(value) + "; but was expected? " + shouldContain, shouldContain, line.contains(value)));
    }

    private List<String> getAllLogLines() {
        return inMemoryAppender.getLogLines();
    }


}
