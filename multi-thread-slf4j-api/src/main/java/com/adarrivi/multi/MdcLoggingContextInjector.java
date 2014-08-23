package com.adarrivi.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Includes in the Mdc context, for the current thread, parameters annotated with IncludedIntoLogContext annotation
 */
public class MdcLoggingContextInjector {

    private static final Logger LOGGER = LoggerFactory.getLogger(MdcLoggingContextInjector.class);
    private static final String EMPTY_STRING = "";

    public static void addIncludedParamsIntoCurrentLoggingContext(Object object) {
        if (object == null) {
            return;
        }
        ClassMemberFinder classMemberFinder = new ClassMemberFinder(object.getClass());
        classMemberFinder.findMembers();
        Map<Field, IncludedIntoLogContext> annotatedFieldMap = classMemberFinder.getAnnotatedFieldMap(IncludedIntoLogContext.class);
        annotatedFieldMap.forEach((field, annotation) -> addAnnotatedFieldIntoCurrentThreadContext(field, annotation, object));
    }

    private static void addAnnotatedFieldIntoCurrentThreadContext(Field field, IncludedIntoLogContext annotation, Object object) {
        MDC.put(getKey(field, annotation), getValueAsString(field, object));
    }

    private static String getKey(Field field, IncludedIntoLogContext annotation) {
        String key = annotation.key();
        if (EMPTY_STRING.equals(key)) {
            key = field.getName();
        }
        return key;
    }

    private static String getValueAsString(Field field, Object object) {
        try {
            return String.valueOf(field.get(object));
        } catch (IllegalAccessException ex) {
            LOGGER.error("Impossible to access the value of the field {}: {}", field.getName(), ex);
            return EMPTY_STRING;
        }
    }
}
