package com.javarush.task.task31.task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

public class Solution {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream(args[0])) {
            ArrayList<FileInputStream> files = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>(Arrays.asList(args));
            names.remove(0);
            names.sort((o1, o2) -> {
                String str1 = o1.substring(o1.lastIndexOf(".") + 1);
                String str2 = o2.substring(o2.lastIndexOf(".") + 1);
                return str1.compareTo(str2);
            });
            for (String file : names) {
                files.add(new FileInputStream(file));
            }
            try (ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(files)))) {
                zis.getNextEntry();
                int len;
                byte[] b = new byte[1024];
                while ((len = zis.read(b)) > 0) {
                    fos.write(b, 0, len);
                }
                fos.flush();
                zis.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
