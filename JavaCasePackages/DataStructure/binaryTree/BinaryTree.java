package binaryTree;

import java.util.ArrayList;

class BinaryTreeNode {
    private char element;//二叉树节点权值
    private BinaryTreeNode leftChild;//二叉树左孩子指针
    private BinaryTreeNode rightChild;//二叉树右孩子指针
    private boolean leftTag;//在线索化二叉树时指示左指针指向左孩子还是前驱，若为true指向前驱
    private boolean rightTag;//在线索化二叉树时指示右指针指向右孩子还是后驱，若为true指向后继

    /**
     * 默认初始化二叉树节点
     */
    public BinaryTreeNode() {
        element = '#';
        leftChild = rightChild = null;
        leftTag = rightTag = false;
    }

    /**
     * 以指定的权值初始化二叉树节点
     *
     * @param theElement 元素
     */
    public BinaryTreeNode(char theElement) {
        element = theElement;
        leftChild = rightChild = null;
        leftTag = rightTag = false;
    }

    /**
     * 获取元素
     *
     * @return char
     */
    public char getElement() {
        return element;
    }

    /**
     * 设置节点权值
     *
     * @param element 给定的权值
     */
    public void setElement(char element) {
        this.element = element;
    }

    /**
     * 获取左孩子
     *
     * @return {@link BinaryTreeNode}
     */
    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * 设置左孩子
     *
     * @param leftChild 给定的左孩子
     */
    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * 获取右孩子
     *
     * @return {@link BinaryTreeNode}
     */
    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    /**
     * 设置右孩子
     *
     * @param rightChild 给定的右孩子
     */
    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * 判断左标签真假
     *
     * @return boolean
     */
    public boolean isLeftTag() {
        return leftTag;
    }

    /**
     * 设置左标签
     *
     * @param leftTag 给定的左标签
     */
    public void setLeftTag(boolean leftTag) {
        this.leftTag = leftTag;
    }

    /**
     * 判断右标签真假
     *
     * @return boolean
     */
    public boolean isRightTag() {
        return rightTag;
    }

    /**
     * 设置右标签
     *
     * @param rightTag 给定的右标签
     */
    public void setRightTag(boolean rightTag) {
        this.rightTag = rightTag;
    }
}

class HuffmanTreeNode {
    private BinaryTreeNode root;//树的根节点
    private int treeSize;//树中的节点个数
    private boolean hasThread;//指示是否经过了线索化
    private ArrayList<Character> orderArray;//在创建该类对象时，会要求输入遍历序列，遍历序列会保存在该容器中

    public int getTreeSize() {
        return treeSize;
    }

    public boolean isHasThread() {
        return hasThread;
    }
}
