package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString() {
        return Arrays.stream(Dish.values()).
                collect(Collectors.toList()).
                toString().replaceAll("[\\[\\]]", "");
    }
}
