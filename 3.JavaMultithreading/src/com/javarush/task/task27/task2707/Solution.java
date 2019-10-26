package com.javarush.task.task27.task2707;

public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread thread = new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                synchronized (o2) {
                }
            }
        });
        Thread thread2 = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));
        thread.start();
        thread2.start();
        Thread.sleep(2000);
        return thread2.getState() != Thread.State.BLOCKED;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
