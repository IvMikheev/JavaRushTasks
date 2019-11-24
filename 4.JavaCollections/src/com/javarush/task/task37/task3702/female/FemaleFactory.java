package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {
        return age > 19 ? new Woman() : age < 13 ? new KidGirl() : new TeenGirl();
    }
}
