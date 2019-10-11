package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/

/*
Инициализируй поле wheels используя данные из loadWheelNamesFromDB.
Выкинь исключение в случае некорректных данных.
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            if (loadWheelNamesFromDB() == null) {
                throw new NullPointerException();
            } else if (loadWheelNamesFromDB().length != 4) {
                throw new IndexOutOfBoundsException();
            } else {
                for (String str : loadWheelNamesFromDB()) {
                    if (str.equals(Wheel.valueOf(str).toString())) {
                        wheels.add(Wheel.valueOf(str));
                    } else throw new IllegalArgumentException();
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        new Car();
    }
}
