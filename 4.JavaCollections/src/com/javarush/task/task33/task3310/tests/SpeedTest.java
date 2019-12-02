package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long startMs = System.currentTimeMillis();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }

        return System.currentTimeMillis() - startMs;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long startMs = System.currentTimeMillis();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }

        return System.currentTimeMillis() - startMs;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> idShort1 = new HashSet<>();
        Set<Long> idShort2 = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        long timeShort1 = getTimeToGetIds(shortener1, origStrings, idShort1);
        long timeShort2 = getTimeToGetIds(shortener2, origStrings, idShort2);

        Assert.assertTrue(timeShort1 > timeShort2);

        origStrings.clear();
        timeShort1 = getTimeToGetStrings(shortener1, idShort1, origStrings);
        origStrings.clear();
        timeShort2 = getTimeToGetStrings(shortener2, idShort2, origStrings);

        Assert.assertEquals(timeShort1, timeShort2, 30);
    }
}
