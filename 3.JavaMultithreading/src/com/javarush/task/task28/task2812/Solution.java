package com.javarush.task.task28.task2812;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            final int localId = i;
            executor.submit(() -> doExpensiveOperation(localId));
        }
        executor.shutdownNow().forEach((r) -> System.out.println(r + " was not completed"));
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}
