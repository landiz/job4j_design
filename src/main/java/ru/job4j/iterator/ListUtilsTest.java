package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 2));
        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;
        ListUtils.removeIf(input, isEvenNumber);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }

    @Test
    public void whenAddReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 2));
        Predicate<Integer> isEvenNumber = x -> x % 2 != 0;
        int valueRemove = 8;
        ListUtils.replaceIf(input, isEvenNumber, 8);
        assertThat(input, is(Arrays.asList(0, 8, 2, 8, 4, 8, 2)));
    }

    @Test
    public void whenAddRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> removeElements = new ArrayList<>(Arrays.asList(3, 4, 5));
        ListUtils.removeAll(input, removeElements);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

}