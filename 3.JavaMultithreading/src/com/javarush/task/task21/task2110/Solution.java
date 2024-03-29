package com.javarush.task.task21.task2110;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* 
Рефакторинг методов
*/

/*
Отрефакторите метод writeZipEntriesToFile в соответствии с java7 try-with-resources.
*/

public class Solution {
    public static void writeZipEntriesToFile(String zipFileName, String outputFileName) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        Path outputFilePath = Paths.get(outputFileName);

        try (ZipFile zip = new ZipFile(zipFileName); BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset)) {
            String newLine = System.getProperty("line.separator");
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                String zipEntryName = ((ZipEntry) entries.nextElement()).getName() + newLine;
                writer.write(zipEntryName, 0, zipEntryName.length());
            }
        }
    }

    public static void main(String[] args) {
    }
}
