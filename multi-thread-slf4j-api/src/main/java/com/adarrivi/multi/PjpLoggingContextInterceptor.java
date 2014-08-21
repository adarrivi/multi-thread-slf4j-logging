package com.adarrivi.multi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;

public class PjpLoggingContextInterceptor {

    public static void addIncludedParametersToLogContext(ProceedingJoinPoint pjp) {

        MDC.clear();
    }


}
