package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();
    private K key;

    public Cache() {
    }

    public Cache(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V v = cache.get(key);
        if (v == null) {
            v = (V) Class.forName(clazz.getName()).getConstructor(key.getClass()).newInstance(key);
            cache.put(key, v);
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException |
                IllegalAccessException |
                InvocationTargetException ignored) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
