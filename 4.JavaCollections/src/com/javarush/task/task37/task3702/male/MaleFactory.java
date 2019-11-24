package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {
        return age > 19 ? new Man() : age <= 12 ? new KidBoy() : new TeenBoy();
    }
}
