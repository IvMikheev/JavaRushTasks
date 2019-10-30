package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Consumer implements Runnable {
    TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(450);
                while (true) {
                    System.out.format("Processing %s%n", queue.take().toString());
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
