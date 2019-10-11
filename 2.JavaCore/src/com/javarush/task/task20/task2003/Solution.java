package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/

/* 
В методе fillInPropertiesMap считайте имя файла с консоли и заполни карту properties данными из файла.
Реализуй логику записи в файл и чтения из файла для карты properties.
*/

public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static String fileName;
    public static Properties prop = new Properties();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        InputStream inputStream = new FileInputStream(fileName);
        load(inputStream);
    }

    public void save(OutputStream outputStream) throws Exception {
        prop.clear();
        properties.forEach((key, value) -> prop.put(key, value));
        prop.store(outputStream, "");

    }

    public void load(InputStream inputStream) throws Exception {
        prop.load(inputStream);
        prop.forEach((key, value) -> properties.put(String.valueOf(key), String.valueOf(value)));
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.fillInPropertiesMap();
        OutputStream outputStream = new FileOutputStream(fileName);
        solution.save(outputStream);
    }
}
