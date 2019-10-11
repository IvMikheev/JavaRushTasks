package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

/*
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        int i = 0;
        while (fileReader.ready()) {
            String[] strings = fileReader.readLine().split("\\W");
            for (String str : strings) {
                if (str.equals("world")) i++;
            }
        }
        reader.close();
        fileReader.close();
        System.out.println(i);
    }
}
