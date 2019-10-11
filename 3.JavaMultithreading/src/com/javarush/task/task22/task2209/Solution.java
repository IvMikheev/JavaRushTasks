package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
Составить цепочку слов
*/

/*
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке, чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
Вывести полученную строку на экран.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        StringBuilder result = getLine(fileReader.readLine().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder sb = new StringBuilder();

        if (words.length == 0) {
            return sb;
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        list.sort(String::compareTo);

        for (int i = 0; i < list.size();) {
            if (list.size() == 0) {
                break;
            }

            if (sb.toString().isEmpty()) {
                sb.append(list.get(0)).append(" ");
                list.remove(list.get(0));
            }

            if (sb.toString().toLowerCase().substring(sb.length() -2, sb.length() -1).
                    endsWith(list.get(i).toLowerCase().substring(0, 1))) {
                sb.append(list.get(i)).append(" ");
                list.remove(list.get(i));
                i = 0;
            } else i++;
        }

        if (list.size() > 0) {
            for (String str : list) {
                sb.append(str).append(" ");
            }
        }

        sb.delete(sb.length() - 1, sb.length());
        return sb;
    }
}
