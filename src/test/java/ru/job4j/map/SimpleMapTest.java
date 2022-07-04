package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddNonNull() {
        Map<String, String> map = new SimpleMap<>();
        map.put("key", "value");
        assertEquals("value", map.get("key"));
    }

    @Test
    public void whenAddThenGetNotExistValue() {
        Map<String, String> map = new SimpleMap<>();
        assertTrue(map.put("key", "value"));
        assertNotEquals("value", map.get("notExitingValue"));
    }

    @Test
    public void whenAddThenRemove() {
        Map<String, String> map = new SimpleMap<>();
        map.put("key", "value");
        assertEquals(true, map.remove("key"));
    }

    @Test
    public void whenAddRemoveThenGet() {
        Map<String, String> map = new SimpleMap<>();
        map.put("key", "value");
        map.remove("key");
        assertNull(map.get("key"));
    }

    @Test
    public void whenAddThenRemoveNotExistValue() {
        Map<String, String> map = new SimpleMap<>();
        map.put("key", "value");
        assertFalse(map.remove("keyNotExist"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Map<String, String> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        map.put("key", "value");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        Map<String, String> map = new SimpleMap<>();
        map.iterator().next();
    }
}
