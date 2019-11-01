package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<Path> files = Files.walk(Paths.get(root)).
                                filter(Files::isRegularFile).
                                collect(Collectors.toList());
        List<String> list = new ArrayList<>();
        files.forEach(path -> list.add(path.toFile().getPath()));
        return list;
    }

    public static void main(String[] args) {
    }
}
