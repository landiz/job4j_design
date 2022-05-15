package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public SimpleArrayList() {
        this.container = (T[]) new Object[2];
    }

    @Override
    public void add(T value) {
        increaseContainer();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1);
        container[container.length - 1] = null;
        modCount++;
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;

        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    private void increaseContainer() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 2);
        } else {
            if (container.length == size) {
                container = Arrays.copyOf(container, container.length * 2);
            }
        }
    }
}