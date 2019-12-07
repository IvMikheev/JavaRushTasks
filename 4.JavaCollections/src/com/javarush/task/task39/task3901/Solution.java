package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        int length = 0;
        if (s != null) {
            if (!s.isEmpty()) {
                length = 1;
                for (int i = 0; i < s.length(); i++) {
                    String string = String.valueOf(s.charAt(i));
                    for (int j = i + 1; j < s.length(); j++) {
                        String letter = String.valueOf(s.charAt(j));
                        if (!string.contains(letter)) {
                            string = string.concat(letter);
                            length = Math.max(length, string.length());
                        } else break;
                    }
                }
            }
        }
        return length;
    }
}
