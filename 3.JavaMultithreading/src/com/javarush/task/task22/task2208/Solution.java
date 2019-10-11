package com.javarush.task.task22.task2208;

import java.util.*;

/* 
Формируем WHERE
*/

/*
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(getQuery(map));

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params == null || params.isEmpty()) {
            return sb.toString();
        }
        params.forEach((s, s2) -> {
            if (s != null && s2 != null) {
                sb.append(s).append(" = '").append(s2).append("' and ");
            }
        });
        if (sb.length() > 5) sb.delete(sb.length()- 5, sb.length());
        return sb.toString();
    }
}
