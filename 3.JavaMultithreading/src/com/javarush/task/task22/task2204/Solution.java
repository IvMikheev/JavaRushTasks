package com.javarush.task.task22.task2204;

/* 
Форматирование строки
*/

/*
Исправить метод getFormattedString так, чтобы он возвращал строку с параметрами для форматирования.
Для перевода каретки не используйте \n.

Должен быть вывод:
20 / 7 = 2,86
Exp = 3,33e+00
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(String.format(getFormattedString(), 20.0 / 7.0, 10.0 / 3.0));
    }

    public static String getFormattedString() {
        return "20 / 7 = %.2f%nExp = %.2e";
    }
}
