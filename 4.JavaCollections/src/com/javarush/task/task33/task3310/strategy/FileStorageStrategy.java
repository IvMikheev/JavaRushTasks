package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getFileBucket(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        if (table[indexFor(hash, table.length)] != null) {
            for (Entry e = table[indexFor(hash, table.length)].getEntry();
                 e != null; e = e.next) {
                Object k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
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
        for (FileBucket fileBucket : src) {
            if (fileBucket != null) {
                Entry e = fileBucket.getEntry();
                if (e != null) {
                    for (Entry next = e.next; e != null; e = e.next) {
                        int i = indexFor(e.hash, newCapacity);
                        if (newTable[i] == null) {
                            newTable[i] = new FileBucket();
                        }
                        e.next = newTable[i].getEntry();
                        newTable[i].putEntry(e);
                        e = next;
                    }
                }
            }
        }
    }

    void addFileBucket(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        } else {
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        }

        if (table[bucketIndex].getFileSize() >= bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    void createFileBucket(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        } else {
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return getFileBucket(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null) return false;

        for (FileBucket fileBucket : table) {
            if (fileBucket != null) {
                for (Entry e = fileBucket.getEntry(); e != null; e = e.next) {
                    if (value.equals(e.value)) return true;
                }
            } else return false;
        }

        return false;
    }

    @Override
    public void put(Long key, String value) {
        addFileBucket(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket fileBucket : table) {
            if (fileBucket != null) {
                for (Entry e = fileBucket.getEntry(); e != null; e = e.next) {
                    if (e.getValue().equals(value)) return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for (FileBucket fileBucket : table) {
            if (fileBucket != null) {
                for (Entry e = fileBucket.getEntry(); e != null; e = e.next) {
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
