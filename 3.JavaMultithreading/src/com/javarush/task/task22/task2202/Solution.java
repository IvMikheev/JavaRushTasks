package com.javarush.task.task22.task2202;

/*
Найти подстроку
*/

/*
Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.

Пример:
"JavaRush - лучший сервис обучения Java."

Результат:
"- лучший сервис обучения"

Пример:
"Амиго и Диего лучшие друзья!"

Результат:
"и Диего лучшие друзья!"

На некорректные данные бросить исключение TooShortStringException (сделать исключением).
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }
        String[] strings = string.split(" ");
        if (strings.length < 5) {
            throw new TooShortStringException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
            } else {
                sb.append(strings[i]).append(" ");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}