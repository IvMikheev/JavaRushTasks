package com.javarush.task.task29.task2913;

import java.util.Random;

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder numbers = new StringBuilder();
        if (a < b) {
            for (int i = a; i <= b; i++) {
                numbers.append(i).append(" ");
            }
        } else {
            for (int i = a; i >= b ; i--) {
                numbers.append(i).append(" ");
            }
        }
        return numbers.replace(numbers.length() - 1, numbers.length(), "").toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}