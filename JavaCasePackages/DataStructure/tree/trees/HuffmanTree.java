package DataStructure.tree.trees;

import DataStructure.tree.nodes.HuffmanTreeNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈夫曼树，由于哈夫曼树不需要执行树的众多操作，此处就不继承AbstractTree接口了
 *
 * @author Mingxiang
 */
public class HuffmanTree {
    private final HuffmanTreeNode root;

    /**
     * 根据给出的权值序列构建哈夫曼树，并将根节点赋值给root节点
     *
     * @param arr 加勒比海盗
     */
    public HuffmanTree(int[] arr) {
        ArrayList<HuffmanTreeNode> huffmanNodes = new ArrayList<>();
        for (int value : arr)
            huffmanNodes.add(new HuffmanTreeNode(value));
        HuffmanTreeNode parentNode = null;
        while (huffmanNodes.size() > 1) {
            Collections.sort(huffmanNodes);
            HuffmanTreeNode leftNode = huffmanNodes.get(0);
            HuffmanTreeNode rightNode = huffmanNodes.get(1);
            parentNode = new HuffmanTreeNode(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeftChild(leftNode);
            parentNode.setRightChild(rightNode);
            huffmanNodes.remove(leftNode);
            huffmanNodes.remove(rightNode);
            huffmanNodes.add(parentNode);
        }
        root = parentNode;
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }
}

class TestHuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree tree = new HuffmanTree(arr);
    }
}
