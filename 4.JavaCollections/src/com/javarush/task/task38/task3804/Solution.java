package com.javarush.task.task38.task3804;


public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFabric.class;
    }

    public static void main(String[] args) {
        System.out.println(ExceptionFabric.ex(ApplicationExceptionMessage.SOCKET_IS_CLOSED));
    }
}