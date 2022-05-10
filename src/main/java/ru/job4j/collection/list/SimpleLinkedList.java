package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;

    private int modCount;

    private Node<E> first;

    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> previousLast = last;
        Node<E> newNode = new Node<>(previousLast, value, null);
        last = newNode;
        if (previousLast == null) {
            first = newNode;
        } else {
            previousLast.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> desiredNode;
        if (index < size / 2) {
            desiredNode = first;
            for (int i = 0; i < index; i++) {
                desiredNode = desiredNode.next;
            }
        } else {
            desiredNode = last;
            for (int i = size - 1; i > index; i--) {
                desiredNode = desiredNode.previous;
            }
        }
        return desiredNode.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E rsl = currentNode.data;
                currentNode = currentNode.next;
                return rsl;
            }

        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        Node(Node<E> previous, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
}
