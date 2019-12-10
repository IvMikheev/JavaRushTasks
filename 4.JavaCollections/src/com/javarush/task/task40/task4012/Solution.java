package com.javarush.task.task40.task4012;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        boolean firstDateLess = firstDate.isBefore(secondDate);
        return firstDateLess ? Period.between(firstDate, secondDate) : Period.between(secondDate, firstDate);
    }
}
