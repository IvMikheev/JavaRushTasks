package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> integerSet = new HashSet<>();
        if (number.matches(".*[\\D].*")) {
            return integerSet;
        }
        String revers = "";
        try {
            for (int i = 2; i <= 36; i++) {
                String ordinal = new BigInteger(number, 10).toString(i);
                for (int j = ordinal.length() - 1; j >= 0; j--) {
                    revers += ordinal.charAt(j);
                }
                if (ordinal.equals(revers)) {
                    integerSet.add(i);
                }
                revers = "";
            }
        } catch (NumberFormatException e) {
            return integerSet;
        }
        return integerSet;
    }
}