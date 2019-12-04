package com.javarush.task.task38.task3809;

public class Solution {
    public static void main(String[] args) throws IllegalAccessException {
        JavaRushBankAccount account = new JavaRushBankAccount("Mr.Smith");
        System.out.println("Проверка №1:");
        ReflectionAnnotationUtil.check(account);

        System.out.println("Проверка №2:");
        account.setAmount(100);
        ReflectionAnnotationUtil.check(account);

        System.out.println("Проверка №3:");
        ReflectionAnnotationUtil.check(new IncorrectAccount());
    }
}
