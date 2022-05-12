package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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
    public void whenAddAfterInTheMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 3, -1, 0, 9, -5));
        ListUtils.addAfter(input, 2, 10);
        assertThat(input, is(Arrays.asList(5, 3, -1, 10, 0, 9, -5)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 2, 0);
    }

    @Test
    public void whenRemoveIfOdd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ListUtils.removeIf(input, x -> x % 2 == 1);
        assertThat(input, is(Arrays.asList(2, 4, 6, 8)));
    }

    @Test
    public void whenRemoveIfLessThanZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-1, 10, 3, 7, -5, -6, 8, -9));
        ListUtils.removeIf(input, x -> x < 0);
        assertThat(input, is(Arrays.asList(10, 3, 7, 8)));
    }

    @Test
    public void whenReplaceIfGreaterThanZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-6,	4, -5, 10, -8));
        ListUtils.replaceIf(input, x -> x > 0, 0);
        assertThat(input, is(Arrays.asList(-6,	0, -5, 0, -8)));
    }

    @Test
    public void whenReplaceIfDivisibleByThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(18,	-25, -29, 7, 3));
        ListUtils.replaceIf(input, x -> x % 3 == 0, 1);
        assertThat(input, is(Arrays.asList(1,	-25, -29, 7, 1)));
    }

    @Test
    public void whenRemoveAllWithoutRepeats() {
        List<Integer> input = new ArrayList<>(Arrays.asList(46,	-43, 27, -27, -6));
        ListUtils.removeAll(input, Arrays.asList(-27, 46));
        assertThat(input, is(Arrays.asList(-43, 27, -6)));
    }

    @Test
    public void whenRemoveAllWithRepeats() {
        List<Integer> input = new ArrayList<>(Arrays.asList(3,	40,	17,	3, 40));
        ListUtils.removeAll(input, Arrays.asList(3, 3, 40));
        assertThat(input, is(Arrays.asList(17)));
    }

    @Test
    public void whenRemoveAllNoCoincidence() {
        List<Integer> input = new ArrayList<>(Arrays.asList(-48, 13, 10, 7,	-28));
        ListUtils.removeAll(input, Arrays.asList(5, 8));
        assertThat(input, is(Arrays.asList(-48, 13, 10, 7, -28)));
    }

    @Test
    public void whenRemoveAllFullCoincidence() {
        List<Integer> input = new ArrayList<>(Arrays.asList(35,	-29, 39, -39, -1));
        ListUtils.removeAll(input, Arrays.asList(-1, -39, 39, -29, 35));
        assertTrue(input.isEmpty());
    }
}