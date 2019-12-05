package com.javarush.task.task38.task3810;

public @interface Revision {
    Date date();

    Author[] authors() default {};
    int revision();
    String comment() default "";
}
