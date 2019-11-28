package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int count = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            if (values.size() < repeatCount) {
                values.add(value);
            } else if (values.size() == repeatCount) {
                values.remove(0);
                values.add(value);
            }
            return values.get(values.size() - 2);
        } else {
            List<V> values = new ArrayList<>();
            values.add(value);
            map.put(key, values);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if (map.get(key) != null) {
            List<V> values = map.get(key);
            V removedValue = values.remove(0);
            if (values.size() == 0) map.remove(key, values);
            return removedValue;
        } else return null;
    }

    @Override
    public Set<K> keySet() {
        //Если возвращать просто map.keySet() валик не пропускает.
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            set.add(entry.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            values.addAll(entry.getValue());
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getValue().contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}