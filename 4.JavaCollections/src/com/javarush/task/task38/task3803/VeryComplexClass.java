package com.javarush.task.task38.task3803;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = 0;
        String s = (String) o;
    }

    public void methodThrowsNullPointerException() {
        Object o = null;
        int i = o.hashCode();
    }

    public static void main(String[] args) {

    }
}
