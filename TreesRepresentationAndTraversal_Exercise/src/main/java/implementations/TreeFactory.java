package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String s : input) {
            int[] ints = Arrays.stream(s.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            addEdge(ints[0], ints[1]);
        }

        return getRoot();
    }

    private Tree<Integer> getRoot() {
        return nodesByKeys.values().stream().filter(node -> node.getParent() == null)
                .findFirst().orElse(null);
    }

    public Tree<Integer> createNodeByKey(int key) {
        Tree<Integer> tree = new Tree<>(key);
        this.nodesByKeys.putIfAbsent(key, tree);
        return nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentTree = this.createNodeByKey(parent);
        Tree<Integer> childTree = this.createNodeByKey(child);

        parentTree.addChild(childTree);
        childTree.setParent(parentTree);
    }
}



