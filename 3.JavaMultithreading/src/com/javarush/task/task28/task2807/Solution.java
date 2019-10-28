package com.javarush.task.task28.task2807;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(1);
        LinkedBlockingQueue<Runnable> list = new LinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            list.add(() -> doExpensiveOperation(integer.getAndIncrement()));
        }
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, list);
        pool.prestartAllCoreThreads();
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
