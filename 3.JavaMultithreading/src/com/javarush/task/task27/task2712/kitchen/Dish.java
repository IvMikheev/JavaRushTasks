package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        return Arrays.stream(Dish.values()).
                collect(Collectors.toList()).
                toString().replaceAll("[\\[\\]]", "");
    }
}
