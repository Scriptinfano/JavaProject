package DataStructure.tree.nodes;

import DataStructure.tree.MyTreeNode;

/**
 * 所有二叉树节点的基类
 *
 * @author Mingxiang
 */
public class BinaryTreeNode<T extends Comparable<T>> extends MyTreeNode<T> {
    protected BinaryTreeNode<T> leftChild;//二叉树左孩子指针
    protected BinaryTreeNode<T> rightChild;//二叉树右孩子指针

    /**
     * 默认初始化二叉树节点，两个指针域均置空
     */
    public BinaryTreeNode() {
        leftChild = rightChild = null;
    }

    /**
     * 以指定的权值初始化二叉树节点，然后将指针域置空
     *
     * @param theElement 元素
     */
    public BinaryTreeNode(T theElement) {
        value = theElement;
        leftChild = rightChild = null;
    }

    /**
     * 获取该节点的权值
     *
     * @return Integer 返回
     */
    public T getValue() {
        return value;
    }

    /**
     * 设置节点权值
     *
     * @param value 给定的权值
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * 获取左孩子
     *
     * @return {@link BinaryTreeNode}
     */
    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    /**
     * 设置左孩子
     *
     * @param leftChild 给定的左孩子
     */
    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * 获取右孩子
     *
     * @return {@link BinaryTreeNode}
     */
    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    /**
     * 设置右孩子
     *
     * @param rightChild 给定的右孩子
     */
    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * 返回以该节点为根节点的树的高度
     *
     * @return int 左右子树高度的较大值就是以该节点为根节点的子树的高度
     */
    public int height() {
        return Math.max(getLeftChild() == null ? 0 : getLeftChild().height(), getRightChild() == null ? 0 : getRightChild().height()) + 1;
    }

    /**
     * 求该节点左子树的高度
     *
     * @return int 左子树的高度
     */
    public int leftHeight() {
        if (getLeftChild() == null)
            return 0;
        return getLeftChild().height();
    }

    /**
     * 求该节点右子树的高度
     *
     * @return int
     */
    public int rightHeight() {
        if (getRightChild() == null)
            return 0;
        return getRightChild().height();
    }
}

