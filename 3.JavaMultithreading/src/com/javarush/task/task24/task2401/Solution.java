package com.javarush.task.task24.task2401;

/* 
Создание своего интерфейса-маркера
*/

/*
1. Создай интерфейс-маркер SelfInterfaceMarker.
2. Создай класс SelfInterfaceMarkerImpl, который реализует SelfInterfaceMarker.
3. Добавь в SelfInterfaceMarkerImpl минимум 2 любых public метода.
4. Создай исключение UnsupportedInterfaceMarkerException, унаследуй его от класса Exception.
5. В методе testClass класса Util: если параметр == null, то выбросьте UnsupportedInterfaceMarkerException.
*/

public class Solution {
    public static void main(String[] args) throws UnsupportedInterfaceMarkerException {
        SelfInterfaceMarkerImpl obj = new SelfInterfaceMarkerImpl();
        Util.testClass(obj);
    }
}
