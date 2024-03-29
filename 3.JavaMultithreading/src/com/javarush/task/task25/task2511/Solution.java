package com.javarush.task.task25.task2511;

import java.util.TimerTask;

public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = ((t, e) -> {
            StringBuilder replacement = new StringBuilder();
            for (int i = 0; i < t.getName().length(); i++) {
                replacement.append("*");
            }
            System.out.println(e.getMessage().replaceAll(t.getName(), replacement.toString()));
        });
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new TimerTask() {
            @Override
            public void run() {
                throw new Error();
            }
        });
        solution.run();
    }
}