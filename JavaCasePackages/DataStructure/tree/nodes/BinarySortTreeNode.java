package DataStructure.tree.nodes;


public class BinarySortTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    private BinarySortTreeNode<T> parent;

    public enum childMark {
        LEFT,//该节点是父节点的左子
        RIGHT,//该节点是父节点的右子
        NONE//该节点没有父节点
    }

    private childMark mark = childMark.NONE;//标识该节点是父节点的什么节点，默认为NONE

    /**
     * 仅初始化节点值的构造器，其parent默认初始化为null
     *
     * @param theData 节点的值
     */
    public BinarySortTreeNode(T theData) {
        super(theData);
        parent = null;
    }

    /**
     * 指定父节点的构造器
     *
     * @param theParent 父节点
     * @param theData   节点数据
     */
    public BinarySortTreeNode(BinarySortTreeNode<T> theParent, T theData) {
        super(theData);
        parent = theParent;
    }

    @Override
    public BinarySortTreeNode<T> getLeftChild() {
        return (BinarySortTreeNode<T>) super.getLeftChild();
    }

    @Override
    public BinarySortTreeNode<T> getRightChild() {
        return (BinarySortTreeNode<T>) super.getRightChild();
    }

    @Override
    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) {
            ((BinarySortTreeNode<T>) rightChild).setParent(this);
            ((BinarySortTreeNode<T>) rightChild).setMark(childMark.RIGHT);
        }
    }

    public BinarySortTreeNode<T> getParent() {
        return parent;
    }

    private void setParent(BinarySortTreeNode<T> theParent) {
        parent = theParent;
    }

    public final childMark getMark() {
        return mark;
    }

    private void setMark(childMark mark) {
        this.mark = mark;
    }

    @Override
    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) {
            ((BinarySortTreeNode<T>) leftChild).setParent(this);
            ((BinarySortTreeNode<T>) leftChild).setMark(childMark.LEFT);
        }
    }
}

