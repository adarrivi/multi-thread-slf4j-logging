package com.adarrivi.multi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Indicates which attributes are going to be added to the MDC logging container
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IncludedIntoLogContext {

    // To specify the key to be used to store the attribute value. If undefined, the attribute name will be used instead.
    String key();

}
