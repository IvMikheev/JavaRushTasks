package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

/*
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид: имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String.
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами.
Заполнить список PEOPLE используя данные из файла.
Закрыть потоки.
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        while (fileReader.ready()) {
            String s = fileReader.readLine();
            if (s.equals("")) continue;
            String name = s.replaceAll(".\\d+", "");
            String[] dateS = s.replaceAll("\\D+\\s", "").split(" ");
            Date date = new Date(dateS[1] + "/" + dateS[0] + "/" + dateS[2]);
            PEOPLE.add(new Person(name, date));
        }
        fileReader.close();
    }
}
