package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread t;
    private State oldState;

    public LoggingStateThread(Thread t) {
        setDaemon(true);
        this.t = t;
        oldState = t.getState();
    }

    @Override
    public void run() {
        System.out.println(t.getState());
        while (t.getState() != State.TERMINATED) {
            if (t.getState() != oldState && t.getState() != State.TERMINATED) {
                System.out.println(t.getState());
                oldState = t.getState();
            }
        }
        System.out.println(t.getState());
    }
}
