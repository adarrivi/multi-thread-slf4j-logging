package com.adarrivi.multi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        for (Field field : allFields) {
            field.setAccessible(true);
        }
    }
}