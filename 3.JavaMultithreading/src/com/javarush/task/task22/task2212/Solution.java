package com.javarush.task.task22.task2212;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;

        Pattern p = Pattern.compile("\\w");
        Matcher m = p.matcher(telNumber);

        if (m.matches()) return false;
        if (telNumber.length() < 11 || telNumber.length() > 15) return false;

        p = Pattern.compile("[0-9]");
        m = p.matcher(telNumber.substring(telNumber.length() - 1));

        if (!m.find()) return false;

        p = Pattern.compile("[()-]");
        m = p.matcher(telNumber);

        if (m.find()) {
            p = Pattern.compile("[-]");
            m = p.matcher(telNumber);
            if (m.find()) {
                if (telNumber.indexOf("-") < telNumber.indexOf("(")) return false;
                int a = 0;
                for (int i = 0; i < telNumber.length(); i++) {
                    if (telNumber.substring(i, i + 1).equals("-")) {
                        a++;
                        if (a > 2) return false;
                    }
                }
            } else {
                p = Pattern.compile("[()]");
                m = p.matcher(telNumber);
                if (m.find()) {
                    if (telNumber.indexOf(")") < telNumber.indexOf("(")) return false;
                    if (telNumber.indexOf(")") - telNumber.indexOf("(") != 4) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(""));
        while (reader.ready()) {
            String line = reader.readLine();
            if (!line.isEmpty()) {
                System.out.println(checkTelNumber(line));
            }
        }
    }
}
