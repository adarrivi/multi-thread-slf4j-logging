package com.adarrivi.multi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a class with methods to easily access its field descriptors and annotations
 */
class ClassMemberFinder {
    private Class<?> givenClass;
    private Collection<Field> allFields = new ArrayList<>();

    ClassMemberFinder(Class<?> givenClass) {
        this.givenClass = givenClass;
    }

    void findMembers() {
        findAllFieldsIncludingInherited();
        setAllFieldsAccessible();
    }

    private void findAllFieldsIncludingInherited() {
        for (Class<?> classToSearch = givenClass; !isObjectClassOrNull(classToSearch); classToSearch = classToSearch.getSuperclass()) {
            allFields.addAll(getAllFieldsFromClass(classToSearch));
        }
    }

    private boolean isObjectClassOrNull(Class<?> targetClass) {
        return targetClass == null || Object.class.equals(targetClass);
    }

    private Collection<Field> getAllFieldsFromClass(Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        return Arrays.asList(fields);
    }

    private void setAllFieldsAccessible() {
        allFields.forEach(field -> field.setAccessible(true));
    }

    public <A extends Annotation, ACLASS extends Class<A>> Map<Field, A> getAnnotatedFieldMap(ACLASS annotationClass) {
        return allFields.stream().filter(field -> field.getAnnotation(annotationClass) != null).collect(Collectors.toMap(Function.<Field>identity(), field -> field.getAnnotation(annotationClass)));
    }

}