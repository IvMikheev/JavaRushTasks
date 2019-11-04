package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        String pathToFile = args[0];
        int posInFile = Integer.parseInt(args[1]);
        String text = args[2];
        try (RandomAccessFile raf = new RandomAccessFile(pathToFile, "rw")) {
            raf.seek(posInFile);
            byte[] b = new byte[text.length()];
            raf.read(b, 0, b.length);
            String textFromFile = new String(b);
            raf.seek(raf.length());
            if (textFromFile.equals(text)) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
