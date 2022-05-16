package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        Set<Integer> set = new SimpleSet<>();
        Assert.assertFalse(set.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        Set<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Assert.assertEquals(Integer.valueOf(1), set.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), set.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> iterator = set.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> iterator = set.iterator();
        set.add(4);
        iterator.next();
    }

}