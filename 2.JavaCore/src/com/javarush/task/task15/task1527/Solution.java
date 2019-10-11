package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        String str = text.substring(text.indexOf("?") + 1);
        String[] strings = str.split("&");

        for (String s : strings) {
            System.out.print(s.split("=")[0] + " ");
        }
        System.out.println();

        for (String s : strings) {
            try {
                if (s.split("=")[0].equals("obj")) {
                    alert(Double.parseDouble(s.split("=")[1]));
                }
            } catch (Exception e) {
                alert(s.split("=")[1]);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}