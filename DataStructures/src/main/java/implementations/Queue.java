package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E element) {
            this.element = element;

        }
    }

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> current = new Node<>(element);
        if (size == 0) {
            this.head = current;
            this.tail = current;
            this.head.next = tail;
            this.tail.previous = head;
            this.tail.next = null;
            this.head.previous = null;
            size++;
            return;
        }

        current.next = head;
        head.previous = current;
        head = current;
        size++;


    }

    @Override
    public E poll() {
        ensureNotEmpty();
        E element = this.tail.element;

        this.tail = this.tail.previous;
        this.tail.next = null;
        size--;


        return element;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        E element = this.tail.element;

        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = this.current.element;
                this.current = this.current.previous;
                return element;
            }
        };
    }
    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
    }
}
