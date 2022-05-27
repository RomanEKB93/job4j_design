package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.collection.ForwardLinked;
import ru.job4j.list.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleMapTest {

    @Test
    public void whenPutEntriesWithDifferentKeysThenTrue() {
       SimpleMap<Integer, String> map = new SimpleMap<>();
       assertTrue(map.put(1, "Shipulin"));
       assertTrue(map.put(2, "Bjorndalen"));
    }

    @Test
    public void whenPutEntriesWithSameKeysThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Shipulin"));
        assertFalse(map.put(1, "Bjorndalen"));
    }

    @Test
    public void whenPutEntriesWithBucketCollisionThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Shipulin"));
        assertFalse(map.put(9, "Svendsen"));
    }

    @Test
    public void whenGetEntriesWithExistingKeysThenValues() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        assertEquals(map.get(2), "Bjorndalen");
        assertEquals(map.get(1), "Shipulin");
    }

    @Test
    public void whenGetEntryWithAbsentKeyThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        assertNull(map.get(3));
    }

    @Test
    public void whenGetEntryWithTheSameBucketButDifferentKeyThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        assertNull(map.get(9));
    }

    @Test
    public void whenRemoveExistingEntryThenTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        assertTrue(map.remove(2));
    }

    @Test
    public void whenRemoveAbsentEntryThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        assertTrue(map.remove(1));
        assertFalse(map.remove(3));
    }

    @Test
    public void whenCheckMapIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Shipulin");
        map.put(2, "Bjorndalen");
        Iterator<Integer> iterator = map.iterator();
        map.put(3, "Boe");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }
}