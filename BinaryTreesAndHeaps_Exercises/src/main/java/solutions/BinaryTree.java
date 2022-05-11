package solutions;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryTree {
    private int key;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.left = first;
        this.right = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {

        List<Integer> firstPath = new ArrayList<>();
        findPathDfs(this, firstPath, first);


        List<Integer> secondPath = new ArrayList<>();

        findPathDfs(this, secondPath, second);

        int min = Math.min(firstPath.size(), secondPath.size());

        int i = 0;
        for (; i < min; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }
        return firstPath.get(i - 1);
    }

    private boolean findPathDfs(BinaryTree tree, List<Integer> path, int value) {
        if (tree == null) {
            return false;
        }

        if (tree.key == value) { return true; }

        path.add(tree.key);


        boolean path1 = findPathDfs(tree.left, path, value);
        if (path1) {
            return true;
        }
        boolean path2 = findPathDfs(tree.right, path, value);
        if (path2) {
            return true;
        }

        path.remove(Integer.valueOf(tree.key));
        return false;

    }

    public List<Integer> topView() {
        Map<Integer, Pair<Integer, Integer>> offsetToValueLavel = new HashedMap();

        traverseTree(this, 0, 1, offsetToValueLavel);
        return offsetToValueLavel.values()
                .stream()
                .map(Pair::getKey)
                .collect(Collectors.toList());
    }

    private void traverseTree(BinaryTree tree, int offset, int level,
                              Map<Integer, Pair<Integer, Integer>> offsetToValueLavel) {
        if (tree == null) {
            return;
        }

        Pair<Integer, Integer> current = offsetToValueLavel.get(offset);

        if (current == null || level < current.getValue()) {
            offsetToValueLavel.put(offset, new Pair<>(tree.key, level));
        }

        traverseTree(tree.left, offset - 1, level + 1, offsetToValueLavel);
        traverseTree(tree.right, offset + 1, level + 1, offsetToValueLavel);

    }
}
