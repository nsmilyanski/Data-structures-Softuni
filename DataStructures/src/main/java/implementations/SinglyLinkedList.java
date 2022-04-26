package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    public class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;

        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> current = new Node<>(element);
        if (head != null) {
            current.next = this.head;
        }
        this.head = current;
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> current = new Node<>(element);
        if (this.head == null) {
            this.head = current;
        }else {
            Node<E> newNode = this.head;

            while (newNode.next != null) {
                newNode =  newNode.next;
            }
            newNode.next = current;
        }
         this.size++;
    }

    @Override
    public E removeFirst() {
        E element = this.head.element;
        Node<E> next = this.head.next;
        this.head.next = null;
        this.head = next;
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        Node<E> current = this.head;

        while (current.next.next != null) {
            current =  current.next;
        }

        Node<E> next = current.next;

        current.next = null;


        current = null;

        this.size --;

        return next.element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        if (size == 1) {
            E element = this.head.element;
            this.head = null;
            return element;
        }
        Node<E> current = this.head;

        while (current.next != null) {
            current = current.next;
        }

        return current.element;
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
            private Node<E>  nodes = head;
            @Override
            public boolean hasNext() {
                return nodes != null;
            }

            @Override
            public E next() {
                E element = nodes.element;
                nodes = nodes.next;
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
