package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifUp(elements.size() - 1);
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        ensureNotEmpty();

        E el = this.elements.get(0);

        Collections.swap(elements, 0, elements.size() - 1);
        this.elements.remove(elements.size() - 1);
        this.heapifDown(0);


        return el;
    }

    private void heapifDown(int index) {

        while (index < this.elements.size() / 2) {
            int child = index * 2 + 1;

            if (child + 1 <= elements.size() - 1 && isLess(elements.get(child + 1), elements.get(child))) {
                child = child + 1;
            }

            if (isLess(this.elements.get(index), this.elements.get(child))) {
                break;
            }

            Collections.swap(elements, child, index);
            index = child;
        }
    }

    private void ensureNotEmpty() {
        if (elements.size() == 0) {
            throw new IllegalStateException("");
        }
    }
    private void heapifUp(int index) {
        int removeIndex = index;

        while (removeIndex > 0 && isLess(this.elements.get(removeIndex), this.getParent(removeIndex))) {
            Collections.swap(elements, removeIndex, getParentIndex(removeIndex));

            removeIndex = getParentIndex(removeIndex);
        }
    }
    private boolean isLess(E child, E parent) {

        int i = child.compareTo(parent);

        if (i > 0) {
            return true;
        }

        return false;
    }
    private E getParent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    private int getLeftIndex(int index) {
        return (index * 2) + 1;
    }

    private int getRightIndex(int index) {
        return (index * 2) + 2;
    }
}
