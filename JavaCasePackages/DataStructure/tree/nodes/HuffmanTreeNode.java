package DataStructure.tree.nodes;

import org.jetbrains.annotations.NotNull;

public class HuffmanTreeNode extends BinaryTreeNode implements Comparable<HuffmanTreeNode>{


    public HuffmanTreeNode(int value) {
        super(value);
    }

    @Override
    public int compareTo(@NotNull HuffmanTreeNode o) {
        return this.getElement()-o.getElement();//从小到大排序
    }
}
