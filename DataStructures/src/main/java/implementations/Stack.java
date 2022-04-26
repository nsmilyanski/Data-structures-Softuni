package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    public class Node<E> {
        private E element;
        private Node<E> previous;

        public Node(E element) {
            this.element = element;

        }
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        if (size == 0) {
            top = newNode;
            size++;
            return;
        }

        newNode.previous = top;

        top = newNode;
        size++;

    }

    @Override
    public E pop() {
        ensureNotEmpty();
        E element = top.element;

        Node<E> previous = top.previous;

        top = previous;

        size--;

        return element;
    }


    @Override
    public E peek() {
        ensureNotEmpty();

        E element = this.top.element;


        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                this.current = current.previous;
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
