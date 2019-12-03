package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public enum Roman {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        private int num;

        Roman(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toCharArray();
        String[] numbers = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            numbers[i] = String.valueOf(chars[i]).toUpperCase();
        }

        int result = 0;
        for (int i = numbers.length - 1; i >= 0; i -= 2) {
            int current = Roman.valueOf(numbers[i]).num;
            if (i == 0) {
                result += current;
                break;
            }
            int previous = Roman.valueOf(numbers[i - 1]).num;
            if (previous < current) {
                result += (current - previous);
            } else {
                result += (current + previous);
            }
        }

        return result;
    }
}
