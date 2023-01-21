package DataStructure.tree.trees;

import DataStructure.tree.nodes.HuffmanTreeNode;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    private final ArrayList<HuffmanTreeNode> huffmanNodes = new ArrayList<>();
    private HuffmanTreeNode root;

    public HuffmanTree(int[] arr) {
        for (int value : arr)
            huffmanNodes.add(new HuffmanTreeNode(value));
        HuffmanTreeNode parentNode = null;
        while (huffmanNodes.size() > 1) {
            Collections.sort(huffmanNodes);
            HuffmanTreeNode leftNode = huffmanNodes.get(0);
            HuffmanTreeNode rightNode = huffmanNodes.get(1);
            parentNode = new HuffmanTreeNode(leftNode.getElement() + rightNode.getElement());
            parentNode.setLeftChild(leftNode);
            parentNode.setRightChild(rightNode);
            huffmanNodes.remove(leftNode);
            huffmanNodes.remove(rightNode);
            huffmanNodes.add(parentNode);
        }
        root = parentNode;
    }


}
class TestHuffmanTree{
    public static void main(String[] args) {
        int []  arr={13,7,8,3,29,6,1};
        HuffmanTree tree=new HuffmanTree(arr);
    }
}
