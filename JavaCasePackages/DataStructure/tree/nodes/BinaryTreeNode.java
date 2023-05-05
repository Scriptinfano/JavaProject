package DataStructure.tree.nodes;

import DataStructure.tree.MyTreeNode;

/**
 * 所有二叉树节点的基类
 *
 * @author Mingxiang
 */
public class BinaryTreeNode<T extends Comparable<T>> extends MyTreeNode<T> {
    /**
     * 二叉树左孩子指针
     */
    protected BinaryTreeNode<T> leftChild;
    /**
     * 二叉树右孩子指针
     */
    protected BinaryTreeNode<T> rightChild;

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
     * 比较两个二叉树节点的值是否相等，由于这个值是泛型，当他是对象的时候请重写equals方法，此时才能比较值的部分是否相等
     *
     * @param obj obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        //判断一个对象是否是某类的实例，建议使用getClass而不是instanceof，instanceof当对象是某类的子类时也会返回true
        //但是这里的子类可能调用此父类方法来比较父类值是否相等，所以仍然采用instanceof来比较该对象是否是该类的子类对象
        if (!(obj instanceof BinaryTreeNode<?>))
            return false;
        return ((BinaryTreeNode<?>) obj).getValue() != null && ((BinaryTreeNode<?>) obj).getValue().equals(this.getValue());
    }

    /**
     * 返回以该节点为根节点的树的高度
     *
     * @return int 左右子树高度的较大值就是以该节点为根节点的子树的高度
     */
    public final int height() {
        return Math.max(getLeftChild() == null ? 0 : getLeftChild().height(), getRightChild() == null ? 0 : getRightChild().height()) + 1;
    }

    /**
     * 求该节点左子树的高度
     *
     * @return int 左子树的高度
     */
    public final int leftHeight() {
        if (getLeftChild() == null)
            return 0;
        return getLeftChild().height();
    }

    /**
     * 求该节点右子树的高度
     *
     * @return int
     */
    public final int rightHeight() {
        if (getRightChild() == null)
            return 0;
        return getRightChild().height();
    }

    /**
     * 输出该节点的信息
     *
     * @return {@link String}
     */
    public String toString() {
        return "{value:" + value.toString() + "}";
    }
}


