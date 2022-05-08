package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T el = container[Objects.checkIndex(index, size)];
        container[index] = newValue;
        return el;
    }

    @Override
    public T remove(int index) {
        T el = container[Objects.checkIndex(index, size)];
        if (index != size - 1) {
        System.arraycopy(container, index + 1,
                container, index, size - index - 1);
         }
        container[--size] = null;
        modCount++;
        return el;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }

        };
    }

    private void grow() {
        container = Arrays.copyOf(container,
                container.length * 2);
    }
}
