package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {

            Tree<E> poll = queue.poll();
            result.add(poll.key);
            for (Tree<E> child : poll.children) {
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> order = new ArrayList<>();

        this.doDfs(this, order);

        return order;
    }

    private void doDfs(Tree<E> tree, List<E> order) {
        for (Tree<E> child : tree.children) {
            this.doDfs(child, order);
        }
        order.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

        Tree<E> tree = findTree(parentKey);

        if (tree == null) {
            throw new IllegalArgumentException();
        }

        tree.children.add(child);
        child.parent = tree;
    }

    private Tree<E> findTree(E parentKey) {
        List<E> keys = orderBfs();


        Deque<Tree<E>> trees = new ArrayDeque<>();


        trees.offer(this);

        while (!trees.isEmpty()) {

            Tree<E> poll = trees.poll();

            if (poll.key.equals(parentKey)) {
                return poll;
            }

            for (Tree<E> child : poll.children) {
                if (child.key.equals(parentKey)) {
                    return child;
                }
                trees.offer(child);
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {

        Tree<E> tree = findTree(nodeKey);

        if (tree == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : tree.children) {
            child.parent = null;
        }

        tree.children.clear();

        Tree<E> parent = tree.parent;

        if (parent != null) {
        parent.children.remove(tree);
        }



    }

    @Override
    public void swap(E firstKey, E secondKey) {

        Tree<E> firstTree = findTree(firstKey);

        Tree<E> secondTree = findTree(secondKey);

        if (firstTree == null || secondTree == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstTree.parent;
        Tree<E> secondParent = secondTree.parent;

        int indexOfFirstTree = firstParent.children.indexOf(firstTree);
        int indexOfSecondTree = secondParent.children.indexOf(secondTree);

        firstTree.parent = secondParent;
        secondTree.parent = firstParent;


        firstParent.children.set(indexOfFirstTree, secondTree);
        secondParent.children.set(indexOfSecondTree, firstTree);


    }
}



