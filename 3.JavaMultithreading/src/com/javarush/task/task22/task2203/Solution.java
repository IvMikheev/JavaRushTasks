package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/

/*
Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/

public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }
        char[] chars = string.toCharArray();
        int howMuch = 0;
        for (char ch : chars) {
            if (ch == '\t') howMuch++;
        }
        if (howMuch < 2) {
            throw new TooShortStringException();
        }
        String[] strings = string.split("\t");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
            } else {
                builder.append(strings[i]);
            }
        }
        return builder.toString();
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java.\t"));
    }
}