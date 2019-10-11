package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

/*
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(args[0]);
        OutputStream os = new FileOutputStream(args[1]);
        byte[] win1251 = new byte[is.available()];
        is.read(win1251);
        String str = new String(win1251, "Windows-1251");
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
        os.write(utf8);
    }
}
