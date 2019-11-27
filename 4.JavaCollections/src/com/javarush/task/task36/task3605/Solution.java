package com.javarush.task.task36.task3605;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> ts = new TreeSet<>();
        String string = "";

        try (InputStream is = new FileInputStream(args[0])) {
            byte[] bytes = new byte[1024];
            while ((is.read(bytes, 0, bytes.length)) != -1) {
                string = string.concat(new String(bytes)).replaceAll("\\W", "").toLowerCase();
            }
        }

        for (char c : string.toCharArray()) {
            ts.add(String.valueOf(c));
        }

        if (ts.size() < 5) {
            System.out.println(ts.toString().replaceAll("\\W", ""));
        } else System.out.println(ts.toString().replaceAll("\\W","").substring(0, 5));
    }
}
