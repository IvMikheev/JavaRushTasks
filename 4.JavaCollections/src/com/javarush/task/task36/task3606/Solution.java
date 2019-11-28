package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(this.packageName).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".class")) {
                hiddenClasses.add(new ClassFromPath().load(file.toPath()));
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        Class<?> clazz = hiddenClasses.stream().filter(v -> v.getSimpleName().
                toLowerCase().startsWith(key.toLowerCase())).findAny().orElse(null);
        if (clazz != null) {
            try {
                Constructor<?> constructor = null;
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                for (Constructor<?> cons : constructors) {
                    constructor = cons;
                    if (constructor.getParameterTypes().length == 0) break;
                }
                constructor.setAccessible(true);
                return (HiddenClass) constructor.newInstance();
            } catch (InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException ignore) {
            }
        }
        return null;
    }

    public static class ClassFromPath extends ClassLoader {
        public Class<?> load(Path path) {
            try {
                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length);
            } catch (IOException ignore) {
            }
            return null;
        }
    }
}

