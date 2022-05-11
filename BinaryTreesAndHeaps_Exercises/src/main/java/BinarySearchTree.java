import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
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
        this.copy(node.getLeft());
        this.copy(node.getRight());

    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private int count;

		public Node(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }
	
	public void eachInOrder(Consumer<E> consumer) {
        nodeInOrder(this.root, consumer);
            
    }

    private void nodeInOrder(Node<E> root, Consumer<E> consumer) {

        nodeInOrder(root.getLeft(), consumer);
        consumer.accept(root.getValue());
        nodeInOrder(root.getRight(), consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if (root == null) {
            this.root = new Node<>(element);
            this.root.count++;
            return;
        }

        Node<E> current = this.root;
        Node<E> previousNode = this.root;

        while (current != null) {
            if (isLess(element, current.value)) {
                current.count++;
                previousNode = current;
                current = current.leftChild;
            }else if (areEqual(element, current.value)) {
                return;
            }else if (isGreatar(element, current.value)) {
                current.count++;
                previousNode = current;
                current = current.rightChild;
            }
        }

        if (isLess(element, previousNode.value)) {
            previousNode.leftChild = new Node<>(element);
            previousNode.leftChild.count++;
        }else {
            previousNode.rightChild = new Node<>(element);
            previousNode.rightChild.count++;
        }

    }


    public boolean contains(E element) {
        List<Boolean> contains = new ArrayList<>();
      containsNode(this.root, element, contains);
      return contains.size() == 0 ? false : contains.get(0);
    }



    public BinarySearchTree<E> search(E element) {
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

    public List<E> range(E lower, E upper) {

        List<E> rangeValues = new ArrayList<>();

        List<Node<E>> nodesValues = new ArrayList<>();

        findAllNodesDfs(this.getRoot(), nodesValues);

        for (Node<E> value : nodesValues) {
            if (isLess(value.getValue(), upper) && isGreatar(value.getValue(), lower)) {
                rangeValues.add(value.getValue());
            }else if (areEqual(value.getValue(), lower) || areEqual(value.getValue(), upper)) {
                rangeValues.add(value.getValue());
            }
        }


      return rangeValues;
    }



    public void deleteMin() {
        ensureNotEmpty();

        removeMinNode();

    }




    public void deleteMax() {
        ensureNotEmpty();
        Node<E> current = this.getRoot();

        while (current.getRight().getRight() != null) {
            current.count--;
            current = current.getRight();

        }

        if (current.getRight().getLeft() != null) {
            current.count--;
            current.rightChild = current.rightChild.leftChild;
            return;
        }
        current.count--;
        current.rightChild = null;

    }

    public int count() {
        return this.root == null ? 0 : this.root.count;
    }

    public int rank(E element) {
        ensureNotEmpty();
        if (!this.contains(element)) {
            return 0;
        }
        List<Integer> values = new ArrayList<>();

        findSmallerNodes(this.getRoot(), element, values);
        return values.get(0);
    }

    private void findSmallerNodes(Node<E> node, E element, List<Integer> values) {
        if (node == null) {
            return;
        }

        if (areEqual(element, node.value)) {
            values.add(node.count);
            return;
        }


        findSmallerNodes(node.getLeft(), element, values);
        findSmallerNodes(node.getRight(), element, values);

    }

    public E ceil(E element) {
        ensureNotEmpty();

        Node<E> node = findNode(element);

        if (node.getRight() != null) {
            return node.getRight().getValue();
        }else if (node.getLeft() == null) {
            return getParent(node);
        }
        return node.getLeft().getValue();
    }

    public E floor(E element) {
        ensureNotEmpty();

        Node<E> node = findNode(element);

        if (node.leftChild != null) {
            return node.getLeft().getValue();
        }else if (node.getRight() == null) {
            return getParent(node);
        }
        return node.getRight().getValue();
    }

    private E getParent(Node<E> node) {
        Node<E> current = this.root;

        while (!areEqual(current.getLeft().getValue(), node.getValue()) &&
                !areEqual(current.getRight().getValue(), node.getValue())) {

            if (isLess(node.getValue(), current.getValue())) {
                current = current.leftChild;
            }else {
                current = current.rightChild;
            }
        }

        return current.getValue();
    }

    private Node<E> findNode(E element) {
        Node<E> current = this.root;

        while (current != null && !areEqual(current.value, element)) {
            if (isLess(element, current.value)) {
                current = current.leftChild;
            }else {
                current = current.rightChild;
            }
        }

        return current;

    }

    private boolean areEqual(E element, E value) {
        return element.compareTo(value) == 0;
    }

    private boolean isLess(E element, E value) {
        return element.compareTo(value) < 0;
    }

    private boolean isGreatar(E element, E value) {
        return element.compareTo(value) > 0;
    }
    private void containsNode(Node<E> node, E element, List<Boolean> contains) {
        if (node == null) {
            return;
        }
        if (areEqual(node.value, element)) {
            contains.add(true);
            return;
        }

        containsNode(node.getLeft(), element, contains);
        containsNode(node.getRight(), element, contains);

    }
    public void findAllNodesDfs(Node<E> node, List<Node<E>> values) {
        if (node == null) {
            return;
        }

        values.add(node);

        findAllNodesDfs(node.getLeft(), values);
        findAllNodesDfs(node.getRight(), values);
    }
    private void ensureNotEmpty() {
        if (this.root == null ) {
            throw new IllegalArgumentException();
        }
    }
    private void removeMinNode() {

        Node<E> current = this.getRoot();

        while (current.getLeft().getLeft() != null) {
            current.count--;
            current = current.getLeft();

        }

        if (current.getLeft().getRight() != null) {
            current.count--;
            current.leftChild = current.leftChild.rightChild;
            return;
        }
        current.count--;
        current.leftChild =null;
    }

}
