package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        int a = x % 2;
        int b = x / 2;
        if (b <= 0) {
            return String.valueOf(a);
        }
        BinaryRepresentationTask b1 = new BinaryRepresentationTask(b);
        BinaryRepresentationTask b2 = new BinaryRepresentationTask(a);
        String res = "";
        b1.fork();
        b2.fork();
        res += b1.join();
        res += b2.join();
        return res;
    }
}
