package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        String[] revers = birthday.split("[.]");
        birthday = "";
        for (int i = revers.length - 1; i >= 0; i--) {
            if (revers[i].length() == 1) revers[i] = "0" + revers[i];
            birthday = birthday.concat(revers[i]);
        }
        LocalDate localDate = LocalDate.parse(birthday, DateTimeFormatter.BASIC_ISO_DATE);
        return localDate.withYear(Year.parse(year).getValue()).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
