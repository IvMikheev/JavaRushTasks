package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T> Map convert(List<? extends Convertable<T>> list) {
        Map result = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            result.put(list.get(i).getKey(), list.get(i));
        }
        return result;
    }
}
