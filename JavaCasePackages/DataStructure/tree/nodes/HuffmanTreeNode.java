package DataStructure.tree.nodes;

import org.jetbrains.annotations.NotNull;

public class HuffmanTreeNode extends BinaryTreeNode<Integer> implements Comparable<HuffmanTreeNode> {


    public HuffmanTreeNode(int value) {
        super(value);
    }

    @Override
    public int compareTo(@NotNull HuffmanTreeNode o) {
        return this.getValue() - o.getValue();//从小到大排序
    }
}
