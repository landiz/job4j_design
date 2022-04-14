package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    static int count;
    private Node<E> last;
    private Node<E> first;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        LinkedList<Integer> numbers = new LinkedList<>();
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<E> elem = first;
            for (int i = 0; i < index; i++) {
                elem = elem.next;
            }
            return elem.item;
        }
    }

    @Override
    public Iterator<E> iterator() {

        int expectedModCount = modCount;

        return new Iterator<E>() {
            private Node<E> lastReturned = new Node<>(null, null, first);
            private Node<E> next = first;

            @Override
            public boolean hasNext() {
                return (lastReturned.next != null);

            }

            @Override
            public E next() {
                if (!(expectedModCount == modCount)) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = next;
                next = next.next;
                return lastReturned.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
            count++;
        }
    }
}