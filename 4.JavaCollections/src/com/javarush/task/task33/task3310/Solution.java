package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 50);
        testStrategy(new OurHashBiMapStorageStrategy(), 50000);
        testStrategy(new HashBiMapStorageStrategy(), 50000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String val : strings) {
            ids.add(shortener.getId(val));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (long id : keys) {
            strings.add(shortener.getString(id));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> randomStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            randomStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        long startMs = new Date().getTime();
        Set<Long> ids = getIds(shortener, randomStrings);
        long resultMs = new Date().getTime() - startMs;
        Helper.printMessage("Result of getIds() method: " + resultMs + "ms.");

        startMs = new Date().getTime();
        Set<String> strings = getStrings(shortener, ids);
        resultMs = new Date().getTime() - startMs;
        Helper.printMessage("Result of getStrings() method: " + resultMs + "ms.");

        boolean equals = randomStrings.containsAll(strings);

        if (equals) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
}
