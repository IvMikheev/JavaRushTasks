package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        Random random = new Random();
        boolean isNum = false;
        boolean isLetter = false;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while (true) {
                char c = (char) random.nextInt(122);
                if (Pattern.matches("[a-z]", String.valueOf(c)) && !isNum && !isLetter) {
                    baos.write(c);
                    isLetter = true;
                    isNum = true;
                } else if (Pattern.matches("\\d", String.valueOf(c)) && isNum) {
                    baos.write(c);
                    isNum = false;
                } else if (Pattern.matches("[A-Z]", String.valueOf(c)) && isLetter) {
                    baos.write(c);
                    isLetter = false;
                }
                if (baos.toByteArray().length == 8) return baos;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}