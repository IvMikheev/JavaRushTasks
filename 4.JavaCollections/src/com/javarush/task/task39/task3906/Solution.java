package com.javarush.task.task39.task3906;

public class Solution {
    public static void main(String[] args) {
        SecuritySystem securitySystem = new SecuritySystem();
        ElectricPowerSwitch electricPowerSwitch = new ElectricPowerSwitch(securitySystem);
        ElectricPowerSwitch light = new ElectricPowerSwitch(new LightBulb());

        light.press();
        light.press();
        electricPowerSwitch.press();
        electricPowerSwitch.press();
    }
}
