package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        String pathToFile = args[0];
        int posInFile = Integer.parseInt(args[1]);
        String text = args[2];
        try (RandomAccessFile raf = new RandomAccessFile(pathToFile, "rw")) {
            if (raf.length() < posInFile) {
                raf.seek(raf.length());
                raf.write(text.getBytes());
            } else {
                raf.seek(posInFile);
                raf.write(text.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
