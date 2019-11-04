package com.javarush.task.task32.task3202;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        StringWriter writer;
        try {
            writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
            System.out.println(writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) {
        if (is == null) {
            return new StringWriter();
        }
        StringWriter writer = new StringWriter();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                writer.write(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }
}