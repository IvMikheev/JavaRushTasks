package com.javarush.task.task30.task3010;

import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        for (int i = 2; i <= 36 ; i++) {
            try {
                new BigInteger(args[0], i);
                System.out.println(i);
                break;
            } catch (Exception ignored) {
            }
            if (i == 36) {
                System.out.println("incorrect");
            }
        }
    }
}