package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> leftTree;
    private BinaryTree<E> rightTree;

    public BinaryTree(E key, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        this.key = key;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftTree;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightTree;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(createPadding(indent) + this.getKey());

        if (getLeft() != null) {
            String left = this.getLeft().asIndentedPreOrder(indent + 2);
            sb.append(System.lineSeparator()).append(left);
        }


        if (getRight() != null) {
            String right = this.getRight().asIndentedPreOrder(indent + 2);
            sb.append(System.lineSeparator()).append(right);
        }
        return sb.toString();
    }



    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();
        list.add(this);

        if (this.getLeft() != null) {
            list.addAll(this.getLeft().preOrder());
        }

        if (this.getRight() != null) {
            list.addAll(this.getRight().preOrder());
        }

        return list;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();

        if (this.getLeft() != null) {
            list.addAll(this.getLeft().inOrder());
        }

        list.add(this);
        if (this.getRight() != null) {
            list.addAll(this.getRight().inOrder());
        }

        return list;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();

        if (this.getLeft() != null) {
            list.addAll(this.getLeft().postOrder());
        }

        if (this.getRight() != null) {
            list.addAll(this.getRight().postOrder());
        }

        list.add(this);
        return list;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

        if (this.getLeft() != null) {
            this.getLeft().forEachInOrder(consumer);
        }

        consumer.accept(this.key);

        if (this.getRight() != null) {
            this.getRight().forEachInOrder(consumer);
        }

    }
    private String createPadding(int indent) {
        StringBuilder sb = new StringBuilder(0);

        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
