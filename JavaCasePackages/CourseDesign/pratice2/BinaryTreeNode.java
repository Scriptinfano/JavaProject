package pratice2;

public class BinaryTreeNode<T extends Comparable<T>> {
    private T element;//二叉树节点权值
    private BinaryTreeNode<T> leftChild;//二叉树左孩子指针
    private BinaryTreeNode<T> rightChild;//二叉树右孩子指针

    /**
     * 默认初始化二叉树节点
     */
    public BinaryTreeNode() {
        leftChild = rightChild = null;
    }

    /**
     * 以指定的权值初始化二叉树节点
     *
     * @param theElement 元素
     */
    public BinaryTreeNode(T theElement) {
        element = theElement;
        leftChild = rightChild = null;
    }

    /**
     * 获取元素
     *
     * @return char
     */
    public T getElement() {
        return element;
    }

    /**
     * 设置节点权值
     *
     * @param element 给定的权值
     */
    public void setElement(T element) {
        this.element = element;
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

}

