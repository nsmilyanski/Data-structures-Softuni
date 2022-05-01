package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);

        this.heapifUp(this.elements.size() - 1);

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

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException("");
        }
        return this.elements.get(0);
    }
}
