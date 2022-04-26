package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private static final int INITIAL_CAPACITY = 7;
    private Object[] elements;
    private int size;
    private int head;
    private int tail;
    private int capacity;

    public ArrayDeque() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.head = INITIAL_CAPACITY/2;
        this.tail = INITIAL_CAPACITY/2;
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(E Element) {
        if (tail == capacity - 1) {
            grow();
        }

        if (isEmpty()) {
            this.elements[this.tail] = Element;
        }else {
        this.elements[++tail] = Element;
        }
        size++;

    }



    @Override
    public void offer(E element) {
        add(element);
    }

    @Override
    public void addFirst(E element) {
        if (head == 0) {
            grow();
        }
        if (isEmpty()) {
            add(element);
        }else {
            this.elements[--this.head] = element;
            size++;
        }
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public void push(E element) {
        add(element);
    }

    @Override
    public void insert(int index, E element) {
        ensureIndex(index);
        if (index == head) {
            addFirst(element);
        }else if (index == tail) {
            addLast(element);
        }

        if (head == 0 || tail + 1 == capacity) {
            grow();
        }

        insertAndSwap(index);

        elements[index + head] = element;

        size++;

    }

    private void insertAndSwap(int index) {
        if (index > size / 2) {
            for (int i = tail + 1; i > head + index; i--) {

                elements[i] = elements[i - 1];

            }

            tail++;

        }else {

            for (int i = index + head; i > head; i--) {

                elements[i - 1] = elements[i];

            }

            this.head--;

        }
    }

    @Override
    public void set(int index, E element) {
        ensureIndex(index);
        elements[index] = element;
    }


    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        if (this.size == 1) {
            return (E) this.elements[tail];
        }else {
            return (E) this.elements[tail - 1];
        }

    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
     return removeLast();
    }

    @Override
    public E get(int index) {
        ensureIndex(index);
        return (E) this.elements[this.head + index];
    }

    @Override
    public E get(Object object) {
        if (isEmpty()) {
            return null;
        }
        for (int i = head; i <= tail ; i++) {
            if (object.equals(elements[i])) {
                return (E) elements[i];
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index);
        if (index > size / 2) {
            swapLeft(index);
            return removeLast();
        }else {
             swapRight(index);

             return removeFirst();
        }
    }



    @Override
    public E remove(Object object) {
        for (int i = head; i <= tail ; i++) {
            if (object.equals(elements[i])) {
                return remove(i - head);
            }
        }
        return null;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = (E)elements[head];
        elements[head] = null;
        if (size != 1) {
        head++;
        }
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E element = (E)elements[tail];
        elements[tail] = null;

        if (size != 1) {
        tail--;
        }
        size--;
        return element;
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
    public void trimToSize() {
        Object[] newArr = new Object[size];

        int index = 0;
        for (int i = head; i <= tail ; i++) {
            newArr[index++] = elements[i];
        }
        this.head = 0;
        this.tail = size - 1;
        this.elements = newArr;

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;
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
    private void grow() {
        int newCapacity = this.capacity * 2 + 1;
        Object[] newArr = new Object[newCapacity];

        int newHead = newCapacity / 2;
        int newTail = newHead + size -1;

        int index = newHead;

        for (int i = head; i <= tail ; i++) {
            newArr[index++] = elements[i];
        }

        this.head = newHead;
        this.tail = newTail;
        this.elements = newArr;
        this.capacity = newCapacity;
    }
    private void ensureIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void swapRight(int index) {
        for (int i = index + head; i > head; i--) {
            Object element = elements[i];

            elements[i] = elements[i - 1];
            elements[i - 1] = element;
        }
    }

    private void swapLeft(int index) {

        for (int i = head + index; i < tail; i++) {
            Object element = elements[i];

            elements[i] = elements[i + 1];

            elements[i + 1] = element;
        }
    }


}
