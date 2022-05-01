package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.copy(root);
    }

    private void copy(Node<E> node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.copy(node.leftChild);
        this.copy(node.rightChild);
    }

    @Override
    public void insert(E element) {

        Node<E> current = this.root;

        if (current == null) {
            this.root = new Node<>(element);
            return;
        }

        Node<E> prev = null;

        while (current != null) {
            prev = current;
            if (isLess(element, current.value)) {
                current = current.leftChild;
            }else if (isLess(current.value,  element)){
                current = current.rightChild;
            }else {
                return;
            }
        }
        current = new Node<>(element);

        if (isLess(prev.value, current.value)) {
            prev.rightChild = current;
        }else {
            prev.leftChild = current;
        }

    }

    @Override
    public boolean contains(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isLess(current.value, element)) {
                current = current.leftChild;
            }else if (isLess(element, current.value)){
                current = current.rightChild;
            }else {
                return true;
            }
        }

        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> node = this.root;

        while (node != null) {

            if (isLess(element, node.value)) {
                node = node.leftChild;
            }else if (isLess(node.value, element)){
                node = node.rightChild;
            }else {
                return new BinarySearchTree<>(node);
            }
        }
        return new BinarySearchTree<>();
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.root.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.root.rightChild;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }

    private boolean isLess(E first, E second) {
        int i = first.compareTo(second);

        if (i < 0) {
            return true;
        }

        return false;
    }
}
