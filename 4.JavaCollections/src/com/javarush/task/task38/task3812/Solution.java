package com.javarush.task.task38.task3812;


import java.lang.annotation.Annotation;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        Annotation annotation = Arrays.stream(c.getAnnotations()).
                filter(v -> v instanceof PrepareMyTest).findFirst().orElse(null);
        if (annotation != null) {
            PrepareMyTest prepareAnnotation = (PrepareMyTest) annotation;
            Arrays.stream(prepareAnnotation.fullyQualifiedNames()).forEach(System.out::println);
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        Annotation annotation = Arrays.stream(c.getAnnotations()).
                filter(v -> v instanceof PrepareMyTest).findFirst().orElse(null);
        if (annotation != null) {
            PrepareMyTest prepareAnnotation = (PrepareMyTest) annotation;
            Arrays.stream(prepareAnnotation.value()).forEach(v -> System.out.println(v.getSimpleName()));
            return true;
        }
        return false;
    }
}
