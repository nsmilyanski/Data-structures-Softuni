package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    List<E> elements;

    public MinHeap() {
        elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        heapifUp(elements.size() - 1);
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return this.elements.get(0);
    }



    @Override
    public E poll() {
        ensureNotEmpty();

        Collections.swap(elements, 0, elements.size() - 1);
        E remove = elements.remove(elements.size() - 1);

        heapifDown(0);


        return remove;
    }

    private void heapifDown(int index) {

        while (index < elements.size() / 2) {
            int swapIndex = getLeftIndex(index);

            if (swapIndex + 1 < elements.size() && isLess(elements.get(swapIndex + 1), elements.get(swapIndex))) {
                swapIndex = swapIndex + 1;
            }

            if (isLess(elements.get(index), elements.get(swapIndex))) {
                break;
            }


            Collections.swap(elements, swapIndex, index);
            index = swapIndex;

        }
    }

    @Override
    public void decrease(E element) {
        int i = elements.indexOf(element);

        elements.get(i).decrease();

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
    private E getParent(int index) {
        return this.elements.get((index - 1) / 2);
    }

    private void heapifUp(int index) {
        int removeIndex = index;

        while (removeIndex > 0 && isLess(this.elements.get(removeIndex), this.getParent(removeIndex))) {
            Collections.swap(elements, removeIndex, getParentIndex(removeIndex));

            removeIndex = getParentIndex(removeIndex);
        }
    }

    private boolean isLess(E child, E parent) {
        return child.compareTo(parent) < 0;
    }

    private void ensureNotEmpty() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
    }

}
