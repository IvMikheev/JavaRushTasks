package com.javarush.task.task29.task2908;

public interface Computable<Argument, Value> {
    Value compute(Argument argument) throws InterruptedException;
}
