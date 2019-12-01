package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException ignored) {
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ignored) {
            return 0;
        }
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            for (Entry e = entry; e != null; e = e.next) {
                oos.writeObject(e);
            }
        } catch (IOException ignored) {
        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                Entry entry = (Entry) ois.readObject();
                while (ois.available() > 0) {
                    entry.next = (Entry) ois.readObject();
                }
                return entry;
            } catch (IOException | ClassNotFoundException ignored) {
            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }
}
