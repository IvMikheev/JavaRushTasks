package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String message;
        while (true) {
            try {
                message = reader.readLine();
                break;
            } catch (IOException e) {
                writeMessage("An error occurred while trying to enter text. Try again.");
            }
        }
        return message;
    }

    public static int readInt() {
        int message;
        while (true) {
            try {
                message = Integer.parseInt(readString());
                break;
            } catch (NumberFormatException e) {
                writeMessage("An error occurred while trying to enter a number. Try again.");
            }
        }
        return message;
    }
}
