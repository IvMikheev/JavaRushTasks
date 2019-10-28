package com.javarush.task.task29.task2903;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(getRunnable(i, concurrentMap)).start();
        }
        sleepOneSecond();
    }

    private static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Runnable getRunnable(final int i, final ConcurrentMap<Integer, String> concurrentMap) {
        return () -> {
            final String name = "Thread #" + i;
            int randomInt = RANDOM.nextInt(20);
            String text = name + " вставил запись для " + randomInt;
            String previousEntry = concurrentMap.putIfAbsent(randomInt, text);

            if (previousEntry != null) {
                System.out.println(name + " хочет обновить " + randomInt + ", однако уже " + previousEntry);
            } else {
                System.out.println(text);
            }
        };
    }
}
