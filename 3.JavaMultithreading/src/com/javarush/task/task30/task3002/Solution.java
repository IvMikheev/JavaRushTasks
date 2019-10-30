package com.javarush.task.task30.task3002;

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16"));
        System.out.println(convertToDecimalSystem("012"));
        System.out.println(convertToDecimalSystem("0b10"));
        System.out.println(convertToDecimalSystem("62"));
    }

    public static String convertToDecimalSystem(String s) {
        if (s.contains("0x")) {
            s = s.substring(s.indexOf("x") + 1);
            return String.valueOf(Integer.parseInt(s, 16));
        } else if (s.contains("0b")) {
            s = s.substring(s.indexOf("b") + 1);
            return String.valueOf(Integer.parseInt(s, 2));
        } else if (s.startsWith("0") && s.length() > 1) {
            return String.valueOf(Integer.parseInt(s, 8));
        } else return String.valueOf(Integer.parseInt(s, 10));
    }
}
