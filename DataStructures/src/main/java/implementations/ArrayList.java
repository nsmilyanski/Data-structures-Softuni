package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[4];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (elements.length == size) {
            increaseArr();
        }

        elements[size] = element;
        size++;
        return true;
    }



    @Override
    public boolean add(int index, E element) {
        ensureIndex(index);
        shrink(index, element);
        size++;

        return true;
    }




    @Override
    public E get(int index) {
        ensureIndex(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        ensureIndex(index);
        E value = (E) elements[index];
        elements[index] = element;
        return value;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index);

        E value = (E) elements[index];

        removeElement(index);

        size--;

        return value;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        if (size == 0) {
            return -1;
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        if (size == 0) {
            return false;
        }

        for (Object o : elements) {
            if (o.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < size;
            }

            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }

    private void shrink(int index, E element) {
        Object[] arr = new Object[elements.length + 1];

        for (int i = 0; i < size ; i++) {
            if (index > i) {
                arr[i] = elements[i];
            }else {
                arr[i + 1] = elements[i];
            }
        }
        arr[index] = element;
        elements = arr;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + elements.length );
        }
    }

    private void removeElement(int index) {
        Object[] arr = new Object[elements.length - 1];

        for (int i = 0; i < arr.length; i++) {
            if (index > i) {
                arr[i] = elements[i];
            }else {
                arr[i] = elements[i + 1];
            }
        }
        elements = arr;

    }

    private void increaseArr() {
        Object[] arr = new Object[size * 2];

        for (int i = 0; i < size; i++) {
            arr[i] = elements[i];
        }
        elements = arr;

    }


}
