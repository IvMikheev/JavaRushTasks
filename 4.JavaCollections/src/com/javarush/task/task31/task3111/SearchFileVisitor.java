package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int minSize;
    private int maxSize;
    private String partOfName;
    private String partOfContent;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String content = new String(Files.readAllBytes(file));
        boolean isFounded = true;
        if (partOfName != null)
            isFounded = file.getFileName().toString().contains(partOfName);
        if (partOfContent != null && isFounded)
            isFounded = content.contains(partOfContent);
        if (maxSize != 0 && minSize != 0 && isFounded)
            isFounded = content.length() < maxSize && content.length() > minSize;
        else if (maxSize != 0 && isFounded)
            isFounded = content.length() < maxSize;
        else if (minSize != 0 && isFounded)
            isFounded = content.length() > minSize;
        if (isFounded) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }
}
