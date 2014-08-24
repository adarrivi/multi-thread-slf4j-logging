package com.adarrivi.multi;

import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MdcLoggingContextInjectorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MdcLoggingContextInjectorTest.class);
    private static final String REQUEST_ID = "asdf232-asdf3-3-asdf";


    private InMemoryAppender inMemoryAppender;
    private IdContainer request;

    @Before
    public void addInMemoryAppender() {
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

    @After
    public void tearDown() {
        removeInMemoryAppender();
        cleanLoggingContext();
    }

    private void removeInMemoryAppender() {
        org.apache.log4j.Logger.getRootLogger().removeAppender(inMemoryAppender);
    }

    private void cleanLoggingContext() {
        MDC.clear();
    }

    @Test
    public void addIncludedToContext_NoIncludedParam_DoNotAddItToLogging() {
        givenNoInjectionRq();
        whenParametersInjectedToLoggingContext();
        whenLoggingInformation();
        thenAllLogLinesShouldContainParamValue(false, REQUEST_ID);
    }

    private void givenNoInjectionRq() {
        request = new RequestWithNoInjectParam(REQUEST_ID);
    }

    private void whenParametersInjectedToLoggingContext() {
        MdcLoggingContextInjector.addIncludedParamsIntoCurrentLoggingContext(request);
    }

    private void whenLoggingInformation() {
        LOGGER.debug("Hello world");
    }

    private void thenAllLogLinesShouldContainParamValue(boolean shouldContain, String value) {
        inMemoryAppender.getLogLines().forEach(line -> Assert.assertEquals("Value [" + value + "] found: " + line.contains(value) + "; but was expected? " + shouldContain, shouldContain, line.contains(value)));
    }


    @Test
    public void addIncludedToContext_IncludedParamDefaultKey_AddParameterIntoLog() {
        givenDefaultInjectionRq();
        whenParametersInjectedToLoggingContext();
        whenLoggingInformation();
        thenAllLogLinesShouldContainParamValue(true, REQUEST_ID);
    }

    private void givenDefaultInjectionRq() {
        request = new RequestWithInjectParamDefault(REQUEST_ID);
    }

    @Test
    public void addIncludedToContext_IncludedParam_AddParameterIntoLog() {
        givenInjectionRq();
        whenParametersInjectedToLoggingContext();
        whenLoggingInformation();
        thenAllLogLinesShouldContainParamValue(true, REQUEST_ID);
    }

    private void givenInjectionRq() {
        request = new RequestWithInjectParamDefault(REQUEST_ID);
    }

    @Test
    public void addIncludedToContext_NullParam_DoNotAddItToLogging() {
        givenNullParam();
        whenParametersInjectedToLoggingContext();
        whenLoggingInformation();
        thenAllLogLinesShouldContainParamValue(false, REQUEST_ID);
    }

    private void givenNullParam() {
        request = null;
    }
}
