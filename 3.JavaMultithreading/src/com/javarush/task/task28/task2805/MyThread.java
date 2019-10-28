package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static final AtomicInteger priority = new AtomicInteger(MIN_PRIORITY);

    public MyThread() {
        if (priority.get() <= MAX_PRIORITY) {
            setPriority(priority.getAndIncrement());
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if (priority.get() <= MAX_PRIORITY) {
            setPriority(priority.getAndIncrement());
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priority.get() <= MAX_PRIORITY) {
            if (priority.get() < group.getMaxPriority()) {
                setPriority(priority.getAndIncrement());
            } else if (priority.get() >= group.getMaxPriority()) {
                setPriority(group.getMaxPriority());
                priority.getAndIncrement();
            }
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(String name) {
        super(name);
        if (priority.get() <= MAX_PRIORITY) {
            setPriority(priority.getAndIncrement());
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (priority.get() <= MAX_PRIORITY) {
            if (priority.get() < group.getMaxPriority()) {
                setPriority(priority.getAndIncrement());
            } else if (priority.get() >= group.getMaxPriority()) {
                setPriority(group.getMaxPriority());
                priority.getAndIncrement();
            }
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (priority.get() <= MAX_PRIORITY) {
            setPriority(priority.getAndIncrement());
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (priority.get() <= MAX_PRIORITY) {
            if (priority.get() < group.getMaxPriority()) {
                setPriority(priority.getAndIncrement());
            } else if (priority.get() >= group.getMaxPriority()) {
                setPriority(group.getMaxPriority());
                priority.getAndIncrement();
            }
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (priority.get() <= MAX_PRIORITY) {
            if (priority.get() < group.getMaxPriority()) {
                setPriority(priority.getAndIncrement());
            } else if (priority.get() >= group.getMaxPriority()) {
                setPriority(group.getMaxPriority());
                priority.getAndIncrement();
            }
        } else {
            priority.set(MIN_PRIORITY);
            setPriority(priority.getAndIncrement());
        }
    }
}
