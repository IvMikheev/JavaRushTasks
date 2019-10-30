package com.javarush.task.task29.task2901;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Solution {
    public static final String DEFAULT_FILE_NAME = "C:/tmp/strange_file_name.tmp";

    private final String localFileName;
    private List<String> contentAsLines;
    private boolean fileLoaded;

    public Solution(String localFileName) {
        this.localFileName = localFileName == null ? DEFAULT_FILE_NAME : localFileName;
    }

    public static void main(String[] args) {
        String fileName = Solution.class.getResource("Solution.java").getPath();
        Solution solution = new Solution(fileName);
        solution.downloadFileContent();
        if (solution.isFileLoaded()) {
            System.out.println(solution.hasExpectedLine("public class Solution {"));
        } else System.out.println(solution.hasExpectedLine(" public class Solution {"));
    }

    public boolean isFileLoaded() {
        return fileLoaded;
    }

    public void downloadFileContent() {
        try {
            contentAsLines = Files.readAllLines((new File(localFileName)).toPath(), Charset.defaultCharset());
            fileLoaded = true;
        } catch (IOException e) {
            System.out.println("Unsuccessful. What a surprise!");
        }
    }

    public boolean hasExpectedLine(String expectedLine) {
        return contentAsLines.contains(expectedLine);
    }
}