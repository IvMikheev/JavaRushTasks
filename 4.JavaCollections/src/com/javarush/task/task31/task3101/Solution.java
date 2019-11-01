package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) throws Exception {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        try (FileOutputStream fos = new FileOutputStream(allFilesContent)) {
            ArrayList<File> files = new ArrayList<>();
            fillFiles(path.getPath(), files);
            files.sort(Comparator.comparing(File::getName));
            for (File file : files) {
                FileInputStream fis = new FileInputStream(file);
                while (fis.available() > 0) {
                    fos.write(fis.read());
                }
                fos.write(System.lineSeparator().getBytes());
                fos.flush();
                fis.close();
            }
        }
    }

    private static void fillFiles(String path, ArrayList<File> files) {
        File tempFile = new File(path);
        for (File file : tempFile.listFiles()) {
            if (file.isDirectory()) {
                fillFiles(file.getAbsolutePath(), files);
            } else if (file.length() <= 50) {
                files.add(file);
            }
        }
    }
}