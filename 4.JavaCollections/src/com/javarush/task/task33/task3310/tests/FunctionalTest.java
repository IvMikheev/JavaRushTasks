package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String str1 = "test1";
        String str2 = "test2";
        String str3 = "test1";

        long id1 = shortener.getId(str1);
        long id2 = shortener.getId(str2);
        long id3 = shortener.getId(str3);

        Assert.assertNotEquals(str1, id2, id1);
        Assert.assertNotEquals(str3, id2, id3);
        Assert.assertEquals(id1, id3);

        String string1 = shortener.getString(id1);
        String string2 = shortener.getString(id2);
        String string3 = shortener.getString(id3);

        Assert.assertEquals(str1, string1);
        Assert.assertEquals(str2, string2);
        Assert.assertEquals(str3, string3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy hmss = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hmss);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy ohmss = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(ohmss);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy fss = new FileStorageStrategy();
        Shortener shortener = new Shortener(fss);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy hbmss = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hbmss);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy dhbmss = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(dhbmss);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy ohbmss = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ohbmss);
        testStorage(shortener);
    }
}
