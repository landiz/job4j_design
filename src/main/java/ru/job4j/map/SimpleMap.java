package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR) {
            expand();
        }
        int i, hashcode;
        boolean rtn = false;
        hashcode = key.hashCode();
        int hash = hash(hashcode);
        i = indexFor(hash);
        if (table[i] == null) {
            table[i] = new MapEntry(key, value);
            count++;
            modCount++;
            rtn = true;
        }
        return rtn;
    }


    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tableExp = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int hashcode = table[i].key.hashCode();
                int hash = hash(hashcode);
                int index = indexFor(hash);
                if (tableExp[index] == null) {
                    tableExp[index] = table[i];
                }
            }
        }
        table = tableExp;
    }

    @Override
    public V get(K key) {
        V rtn = null;
        int hashcode = key.hashCode();
        int hash = hash(hashcode);
        int index = indexFor(hash);
        if (table[index] != null && table[index].key.equals(key)) {
            rtn = table[index].value;
        }
        return rtn;
    }

    @Override
    public boolean remove(K key) {
        boolean rtn = false;
        int hashcode = key.hashCode();
        int hash = hash(hashcode);
        int index = indexFor(hash);
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            modCount++;
            rtn = true;
        }
        return rtn;
    }

    @Override
    public Iterator<K> iterator() {

        int expectedModCount = modCount;

        return new Iterator<K>() {
            private int pos;
            private int index;

            @Override
            public boolean hasNext() {
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                return this.exist() == 0;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index].key;
            }

            private Integer exist() {
                int value = -1;
                for (int index = this.pos; index < table.length; index++) {
                    if (table[index] != null) {
                        this.pos = index;
                        value++;
                        break;
                    }
                }
                return value;
            }
        };

    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}