package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        int c;
        while ((c = reader.read()) != -1) {
            sw.write((char) c + key);
        }
        return sw.toString();
    }
}
