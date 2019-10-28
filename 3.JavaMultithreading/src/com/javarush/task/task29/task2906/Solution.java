package com.javarush.task.task29.task2906;

public class Solution {
    public static void main(String[] args) {
        Integer a = getValue(Boolean.TRUE, Boolean.TRUE);
        Integer b = getValue(Boolean.FALSE, Boolean.TRUE);
        Integer c = getValue(Boolean.FALSE, Boolean.FALSE);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static Integer getValue(boolean first, boolean second) {
        return first ? (Integer) 100 : second ? 200 : null;
    }
}
