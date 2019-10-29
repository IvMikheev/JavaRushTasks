package com.javarush.task.task29.task2908;

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Square square = new Square();
        CacheComputeManager<Integer, Integer> manager = new CacheComputeManager(square);

        for (int i = 0; i < 8; i++) {
            int j = i % 4;
            Integer result = manager.compute(j);
            System.out.format("%d * %d = %d\n", j, j, result);
        }

        Copyright copyright = new Copyright();
        CacheComputeManager manager2 = new CacheComputeManager(copyright);
        System.out.println(manager2.compute(new Copyright.Period(3012, 3147)));
        System.out.println(manager2.compute(new Copyright.Period(3012, 3146)));
        System.out.println(manager2.compute(new Copyright.Period(3012, 3147)));
    }
}
