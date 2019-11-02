package com.javarush.task.task31.task3112;

import java.nio.file.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:/Java/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        InputStream is = url.openStream();
        Path tempPath = Files.createTempFile("my_temp_", ".tmp");
        Files.copy(is, tempPath, StandardCopyOption.REPLACE_EXISTING);
        String fileName = urlString.substring(urlString.lastIndexOf("/"));
        Path dest = Paths.get(downloadDirectory.toString() + fileName);
        Files.move(tempPath, dest, StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }
}
