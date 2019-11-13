package com.javarush.task.task27.task2712.ad;

public class AdvertisementStorage {
    private static AdvertisementStorage adv;

    private AdvertisementStorage() {}

    public static AdvertisementStorage getInstance() {
        if (adv == null) {
            adv = new AdvertisementStorage();
        }
        return adv;
    }
}
