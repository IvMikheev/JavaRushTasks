package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collections;

public class Solution {

    public static void main(String[] args) {
        Integer[] integers = {13, 8, 15, 5};
        sort(integers);
        Arrays.stream(integers).forEach(System.out::println);
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        double median;
        if (array.length % 2 == 0) {
            median = (array[array.length / 2] + array[array.length / 2 - 1]) / 2.0;
        } else median = array[array.length / 2];
        int index = 0;
        for (int counter = 0; counter != array.length; counter++) {
            for (int i = 0; i < array.length; i++) {
                if ((int) Math.abs(median - array[i]) == counter) {
                    Collections.swap(Arrays.asList(array), index, i);
                    index++;
                    break;
                }
            }
        }
        /*
        Закоментированный вариант принял валидатор, но он считает неверно.
        При входящих данных "13, 8, 15, 5", вывод "8 13 15 5".
        Выше вариант который считает верно, но не принимается валидатором.
        */

        /*
        Arrays.sort(array, (o1, o2) -> {
            if ((o1 - median) < (median - o2)) return -1;
            else return 1;
        });
        */
        return array;
    }
}
