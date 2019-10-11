package com.javarush.task.task24.task2408;

import java.util.List;

/* 
Как избежать Copy+Paste
*/

/*
В классе Dog реализуй логику метода toSayable, которая описана в джавадоке.
*/

public class Solution {
    public static void main(String[] args) {
        List<Pet> pet = Util.getPets();
        List<Sayable> pets = Util.convertPetToSayable(pet);
        Util.printDialog(pets);
    }
}
