package com.javarush.task.task22.task2210;

/* 
StringTokenizer
*/

/*
Используя StringTokenizer разделить query на части по разделителю delimiter.

Пример
getTokens("level22.lesson13.task01", ".")

возвращает массив строк
{"level22", "lesson13", "task01"}
*/

import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        String[] strings = getTokens("level22.lesson13.task01", ".");
        for (String str : strings) {
            System.out.println(str);
        }
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer st = new StringTokenizer(query, delimiter);
        String[] strings = new String[st.countTokens()];

        for (int i = 0; st.hasMoreTokens(); i++) {
            strings[i] = st.nextToken();
        }

        return strings;
    }
}
