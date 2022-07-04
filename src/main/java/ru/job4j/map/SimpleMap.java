package ru.job4j.map;

import java.util.Arrays;
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
        if (count >= capacity * 0.75) {
            expand();
        }
        int i, hashcode;
        boolean rtn = false;
        hashcode = key.hashCode();
        int hash = hash(hashcode);

        if (table[indexFor(hash)] == null) {
            i = indexFor(hash);
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
        int n = table.length;
        return (n - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tableExp = new MapEntry[capacity];
        table = Arrays.copyOf(table, capacity);
        for (int i = 0; i < capacity / 2; i++) {
            if (table[i] != null) {
                int hashcode = table[i].key.hashCode();
                int hash = hash(hashcode);
                int index = indexFor(hash);
                if (tableExp[index] == null) {
                    tableExp[index] = new MapEntry(table[i].key, table[i].value);
                }
            }
        }
        table = Arrays.copyOf(tableExp, capacity);
    }

    @Override
    public V get(K key) {
        V rtn = null;
        int hashcode = key.hashCode();
        int hash = hash(hashcode);
        int index = indexFor(hash);
        if (table[index] != null) {
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
        if (table[index] != null) {
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
            private int index;

            @Override
            public boolean hasNext() {
                boolean rtn = false;
                for (int j = index; j < table.length; j++) {
                    if (table[j] != null) {
                        rtn = true;
                        break;
                    }
                    j++;
                }
                return rtn;
            }

            @Override
            public K next() {
                if ((expectedModCount != modCount)) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[index];
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