package implementations;

import interfaces.Reversed;

import java.util.Iterator;

public class ReversedList<E> implements Reversed<E> {

    private static final int INITIAL_CAPACITY = 2;

    private Object[] elements;
    private int size;
    private int capacity;

    public ReversedList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(E element) {
        if (capacity == size) {
            grow();
        }

        elements[size] = element;
        size++;
    }

    private void grow() {
        int newCapacity = this.capacity * 2;
        Object[] newArr = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = elements[i];
        }

        elements = newArr;


        this.capacity = newCapacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public E get(int index) {
        return (E) elements[size - index - 1];
    }

    @Override
    public void removeAt(int index) {
        ensureIndex(index);

        removeIndex(size - index - 1);
        size--;
    }

    private void removeIndex(int index) {

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
    }

    private void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}
