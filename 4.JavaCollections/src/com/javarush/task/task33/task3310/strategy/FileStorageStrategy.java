package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getFileBucket(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        try {
            Entry e = table[indexFor(hash, table.length)].getEntry();
            for (; e != null; e = e.next) {
                Object k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
        } catch (NullPointerException ex) {
            return null;
        }
        return null;
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        for (FileBucket fb : table) {
            if (fb != null) fb.remove();
        }
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            if (src[j] != null) {
                Entry e = src[j].getEntry();
                if (e != null) {
                    do {
                        Entry next = e.next;
                        int i = indexFor(e.hash, newCapacity);
                        if (newTable[i] == null) newTable[i] = new FileBucket();
                        e.next = newTable[i].getEntry();
                        newTable[i].putEntry(e);
                        e = next;
                    } while (e != null);
                }
            }
        }
    }

    void addFileBucket(int hash, Long key, String value, int bucketIndex) {
        try {
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        } catch (NullPointerException ignored) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        }
        size = (int) table[bucketIndex].getFileSize();
        if (size >= bucketSizeLimit)
            resize(2 * table.length);
    }

    void createFileBucket(int hash, Long key, String value, int bucketIndex) {
        try {
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        } catch (NullPointerException ex) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        }
        //size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getFileBucket(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;

        FileBucket[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            try {
                for (Entry e = tab[i].getEntry(); e != null; e = e.next) {
                    if (value.equals(e.value)) return true;
                }
            } catch (NullPointerException ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addFileBucket(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry e = table[i].getEntry();
                for (; e != null; e = e.next) {
                    if (e.getValue().equals(value)) return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry e = table[i].getEntry();
                for (; e != null; e = e.next) {
                    if (e.getKey().equals(key)) return e.getValue();
                }
            }
        }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
