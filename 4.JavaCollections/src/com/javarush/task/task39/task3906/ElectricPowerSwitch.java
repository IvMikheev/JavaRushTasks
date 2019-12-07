package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private Switchable switchable;

    public ElectricPowerSwitch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void press() {
        System.out.println("Power switch flipped.");
        if (switchable instanceof SecuritySystem) {
            SecuritySystem security = (SecuritySystem) switchable;
            if (security.isOn()) {
                security.turnOff();
            } else {
                security.turnOn();
            }
        } else {
            LightBulb lightBulb = (LightBulb) switchable;
            if (lightBulb.isOn()) {
                lightBulb.turnOff();
            } else {
                lightBulb.turnOn();
            }
        }
    }
}
