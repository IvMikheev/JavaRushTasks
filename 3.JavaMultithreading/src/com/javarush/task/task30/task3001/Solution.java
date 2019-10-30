package com.javarush.task.task30.task3001;

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        if (number.getDigit().contains(".") || number.getDigit().contains("-") || number.getDigit().isEmpty()) {
            throw new NumberFormatException();
        }
        int currentNumberSystem = number.getNumberSystem().getNumberSystemIntValue();
        int expectNumberSystem = expectedNumberSystem.getNumberSystemIntValue();
        String converted = new BigInteger(number.getDigit(), currentNumberSystem).toString(expectNumberSystem);
        return new Number(expectedNumberSystem, converted);
    }
}
