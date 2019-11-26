package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes) {
            if (hasExpectedInterface(clazz) &
                    Modifier.isPrivate(clazz.getModifiers()) & Modifier.isStatic(clazz.getModifiers())) {
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    Method method = clazz.getDeclaredMethod("get", Integer.TYPE);
                    constructor.setAccessible(true);
                    method.setAccessible(true);
                    method.invoke(Collections.EMPTY_LIST, 0);
                } catch (NoSuchMethodException |
                        IllegalAccessException |
                        InvocationTargetException e) {
                    if (e.getCause() != null) {
                        if (e.getCause().getClass().equals(IndexOutOfBoundsException.class)) return clazz;
                    }
                }
            }
        }
        return null;
    }

    private static boolean hasExpectedInterface(Class clazz) {
        boolean contains = false;
        if (Arrays.asList(clazz.getInterfaces()).contains(List.class)) {
            contains = true;
        } else if (clazz.getSuperclass() != null) {
            return hasExpectedInterface(clazz.getSuperclass());
        }
        return contains;
    }
}
