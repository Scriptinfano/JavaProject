package binaryTree.nodes;


public class BinarySortTreeNode<T extends Comparable<T>> {
    private BinarySortTreeNode<T> parent;
    private BinarySortTreeNode<T> leftChild;
    private BinarySortTreeNode<T> rightChild;

    public enum childMark {
        LEFT,//该节点是父节点的左子
        RIGHT,//该节点是父节点的右子
        NONE//该节点没有父节点
    }

    private T data;

    private childMark mark;//标识该节点是父节点的什么节点

    public BinarySortTreeNode() {
    }

    public BinarySortTreeNode(BinarySortTreeNode<T> theParent, T theData) {
        data = theData;
        parent = theParent;
    }

    public void setParent(BinarySortTreeNode<T> theParent) {
        parent = theParent;
    }

    public BinarySortTreeNode<T> getParent() {
        return parent;
    }

    public BinarySortTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinarySortTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinarySortTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinarySortTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public childMark getMark() {
        return mark;
    }

    public void setMark(childMark mark) {
        this.mark = mark;
    }
}

