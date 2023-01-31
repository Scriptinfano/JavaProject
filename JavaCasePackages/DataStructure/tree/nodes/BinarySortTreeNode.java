package DataStructure.tree.nodes;


public class BinarySortTreeNode extends BinaryTreeNode<Integer> {
    private BinarySortTreeNode parent;

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
    public BinarySortTreeNode(Integer theData) {
        super(theData);
        parent = null;
    }

    /**
     * 指定父节点的构造器
     *
     * @param theParent 父节点
     * @param theData   节点数据
     */
    public BinarySortTreeNode(BinarySortTreeNode theParent, Integer theData) {
        super(theData);
        parent = theParent;
    }

    @Override
    public BinarySortTreeNode getLeftChild() {
        return (BinarySortTreeNode) super.getLeftChild();
    }

    @Override
    public BinarySortTreeNode getRightChild() {
        return (BinarySortTreeNode) super.getRightChild();
    }

    @Override
    public void setRightChild(BinaryTreeNode<Integer> rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) {
            ((BinarySortTreeNode) rightChild).setParent(this);
            ((BinarySortTreeNode) rightChild).setMark(childMark.RIGHT);
        }
    }

    public BinarySortTreeNode getParent() {
        return parent;
    }

    private void setParent(BinarySortTreeNode theParent) {
        parent = theParent;
    }

    public final childMark getMark() {
        return mark;
    }

    private void setMark(childMark mark) {
        this.mark = mark;
    }

    @Override
    public void setLeftChild(BinaryTreeNode<Integer> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) {
            ((BinarySortTreeNode) leftChild).setParent(this);
            ((BinarySortTreeNode) leftChild).setMark(childMark.LEFT);
        }
    }
}

