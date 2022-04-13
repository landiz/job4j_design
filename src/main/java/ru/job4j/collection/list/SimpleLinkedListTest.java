package ru.job4j.collection.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {


    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenAddIterHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(true));
    }

    @Test
    public void whenAddIterNextOne() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), Is.is(1));
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(false));
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), Is.is(true));
        assertThat(it.hasNext(), Is.is(true));
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), Is.is(1));
        assertThat(it.next(), Is.is(2));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        list.add(4);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromEmptyListThenGetException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.get(5);
    }

    @Test
    public void whenCheckIterator() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(1), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(2), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(Integer.valueOf(3), it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        list.add(null);
        Assert.assertNull(list.get(0));
        Assert.assertNull(list.get(1));
    }

}