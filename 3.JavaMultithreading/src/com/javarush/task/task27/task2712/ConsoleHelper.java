package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String message = "";
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static List<Dish> getAllDishesForOrder() {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Please select a dish.");
        writeMessage(Dish.allDishesToString());
        String dish;
        while (true) {
            dish = readString();
            if (dish.equalsIgnoreCase("exit")) {
                break;
            }
            if (!dish.isEmpty()) {
                try {
                    dishes.add(Dish.valueOf(dish));
                } catch (IllegalArgumentException e) {
                    writeMessage("Dish not found, try again.");
                }
            } else writeMessage("Dish not found, try again.");
        }
        return dishes;
    }
}
