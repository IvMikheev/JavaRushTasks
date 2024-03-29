package com.javarush.task.task24.task2409;

import java.util.List;

/*
Интернет-магазин продажи джинсов
*/

/*
1. Создай 2 интерфейса в отдельных файлах:
1.1) Item с методами int getId(), double getPrice(), String getTM()
1.2) Jeans extends Item с методами int getLength() и int getSize()

2. В классе Util в методе getAllJeans добавь пропущенную часть java-кода:
2.1) разберись в том, что уже есть в методе getAllJeans класса Util
2.2) создай абстрактный class AbstractJeans от интерфейса Jeans с одним абстрактным методом, реализуй остальные методы
2.3) создай классы Levis и Denim от AbstractJeans, реализуй оставшийся метод
2.4) в классе AbstractJeans реализуй метод toString()
2.5) метод toString класса AbstractJeans должен начинаться с имени подкласса, например, Levis{id=1, length=34, size=6, price=150.0}
*/

public class Solution {

    public static List<Jeans> allJeans = Util.getAllJeans();

    public static void main(String[] args) {
        for (Jeans jeans : allJeans) {
            System.out.println(jeans);
        }
    }

}
