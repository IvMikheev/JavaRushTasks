package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Solution extends SimpleFileVisitor<Path> {
    private static long filesCount = 0;
    private static long directoriesCount = 0;
    private static long size = 0;

    public static void main(String[] args) throws IOException {
        final Solution solution = new Solution();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Path path = Paths.get(reader.readLine());
            if (!Files.isDirectory(path)) {
                System.out.println(path.toAbsolutePath() + " - не папка");
            } else {
                Files.walkFileTree(path, solution);
                System.out.format("Всего папок - %s\nВсего файлов - %s\nОбщий размер - %s", directoriesCount - 1, filesCount, size);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        size += attrs.size();
        filesCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        directoriesCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}
