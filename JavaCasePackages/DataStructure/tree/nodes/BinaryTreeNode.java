package DataStructure.tree.nodes;

public class BinaryTreeNode {
    private Integer element;//二叉树节点权值
    private BinaryTreeNode leftChild;//二叉树左孩子指针
    private BinaryTreeNode rightChild;//二叉树右孩子指针

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
    public BinaryTreeNode(Integer theElement) {
        element = theElement;
        leftChild = rightChild = null;
    }

    /**
     * 获取该节点的权值
     * @return Integer 返回
     */
    public Integer getElement() {
        return element;
    }

    /**
     * 设置节点权值
     *
     * @param element 给定的权值
     */
    public void setElement(Integer element) {
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

}

