package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private static int counter = 0;
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            this.setParent(this);
        }

    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        traversWithDfs(sb, 0, this);

        return sb.toString().trim();
    }

    @Override
    public List<E> getLeafKeys() {

        List<E> values = new ArrayList<>();

        traversWithBfs(values, this);
        return values;

    }



    @Override
    public List<E> getMiddleKeys() {
        List<E> values = new ArrayList<>();


     getMiddleKeysWithBfs(values, this);


        return values;
    }



    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> tree = new ArrayList<>();

        findDeepestNodeWithDfs(this, tree);
        return tree.get(tree.size() - 1);
    }

    private void findDeepestNodeWithDfs(Tree<E> tree, List<Tree<E>> currentTree) {


        int maxvalue = 0;
        int level = 0;

            Deque<Tree<E>> deque = new ArrayDeque<>();
            deque.push(tree);

            while (!deque.isEmpty()) {
                Tree<E> pop = deque.pop();
                level++;

                for (Tree<E> child : pop.children) {
                    if (child.children.isEmpty() && level > maxvalue ) {
                        maxvalue = level;
                        currentTree.add(child);
                    }
                    deque.push(child);
                }
            }

    }

    @Override
    public List<E> getLongestPath() {
        List<Tree<E>> currentTree = new ArrayList<>();

        findDeepestNodeWithDfs(this, currentTree);

        List<E> nodes = new ArrayList<>();

        Tree<E> tree = currentTree.get(currentTree.size() - 1);

        while (tree.parent != null) {
            nodes.add(tree.key);
            tree = tree.parent;
        }
        nodes.add(tree.key);

        Collections.reverse(nodes);
        return nodes;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<Tree<E>> currentTree = new ArrayList<>();

        findDeepestNodeWithDfs(this, currentTree);

        List<List<E>> nodes = new ArrayList<>();



        while (!currentTree.isEmpty()) {

              List<E> currentNodes = new ArrayList<>();
              Tree<E> removedTree = currentTree.remove(currentTree.size() - 1);

              int currentSum = 0;

              while (removedTree.parent != null) {
                  currentSum += (int)removedTree.key;
                  currentNodes.add(removedTree.key);
                  removedTree = removedTree.parent;
              }
              currentNodes.add(removedTree.key);
              currentSum += (int)removedTree.key;

              if (sum == currentSum) {
                  Collections.reverse(currentNodes);
                  nodes.add(currentNodes);
              }

        }


        return nodes;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> middleTrees = new ArrayList<>();

        getMiddleTreesWithBfs( middleTrees, this);

        List<Tree<E>> nodes = new ArrayList<>();



        while (!middleTrees.isEmpty()) {
            int currentSum = 0;

            Tree<E> removedTree = middleTrees.remove(middleTrees.size() - 1);

            currentSum += (int)removedTree.key;

            for (Tree<E> child : removedTree.children) {
                currentSum += (int)child.key;
                if (!child.children.isEmpty()) {
                    middleTrees.add(child);
                }
            }


            if (sum == currentSum) {
               nodes.add(removedTree);
            }

        }

        Collections.reverse(nodes);


        return nodes;
    }
    private void traversWithDfs(StringBuilder sb, int level, Tree<E> tree) {

        sb.append(addEmptySpaces(level))
                .append(tree.key).append(System.lineSeparator());


        for (Tree<E> child : tree.children) {
            traversWithDfs(sb, level + 2, child);
        }


    }

    private String addEmptySpaces(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    private void traversWithBfs(List<E> values, Tree<E> tree) {

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(tree);

        while (!queue.isEmpty()) {
            Tree<E> poll = queue.poll();

            for (Tree<E> child : poll.children) {
                if (child.children.isEmpty()) {
                    values.add(child.getKey());
                }
                queue.offer(child);
            }
        }
    }
    private void getMiddleKeysWithBfs(List<E> values, Tree<E> tree) {

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(tree);

        while (!queue.isEmpty()) {
            Tree<E> poll = queue.poll();

            for (Tree<E> child : poll.children) {
                if (child.parent != null && child.children.size() >= 1) {
                    values.add(child.key);
                }
                queue.offer(child);
            }
        }
    }

    private void getMiddleTreesWithBfs(List<Tree<E>> values, Tree<E> tree) {

        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(tree);

        while (!queue.isEmpty()) {
            Tree<E> poll = queue.poll();

            for (Tree<E> child : poll.children) {
                if (child.parent != null && child.children.size() >= 1) {
                    values.add(child);
                }
                queue.offer(child);
            }
        }
    }
}



