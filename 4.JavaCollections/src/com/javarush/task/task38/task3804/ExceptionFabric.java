package com.javarush.task.task38.task3804;

public class ExceptionFabric {

    public static Throwable ex(Enum e) {
        if (e == null) {
            return new IllegalArgumentException();
        }
        String name = e.name().replaceAll("_", " ").toLowerCase();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        if (e instanceof ApplicationExceptionMessage) {
            return new Exception(name);
        } else if (e instanceof DatabaseExceptionMessage) {
            return new RuntimeException(name);
        } else if (e instanceof UserExceptionMessage) {
            return new Error(name);
        } else return new IllegalArgumentException();
    }
}
