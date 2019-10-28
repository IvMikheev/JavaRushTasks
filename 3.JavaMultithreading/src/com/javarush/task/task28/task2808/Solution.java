package com.javarush.task.task28.task2808;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));
        }

        futures.add(executor.submit(getTask(10000000)));

        for(Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static Callable<String> getTask(final int i) {
        return () -> {
            long sum = 0;
            for (int j = 1; j <= i; j++) {
                sum += j;
            }
            return String.valueOf(sum);
        };
    }
}
