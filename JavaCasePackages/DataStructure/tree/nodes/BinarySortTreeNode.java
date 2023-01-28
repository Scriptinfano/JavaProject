package DataStructure.tree.nodes;


public class BinarySortTreeNode extends BinaryTreeNode<Integer> {
    private BinarySortTreeNode parent;

    public enum childMark {
        LEFT,//该节点是父节点的左子
        RIGHT,//该节点是父节点的右子
        NONE//该节点没有父节点
    }

    private childMark mark;//标识该节点是父节点的什么节点

    public BinarySortTreeNode() {
        super();
    }

    public BinarySortTreeNode(BinarySortTreeNode theParent, Integer theData) {
        super(theData);
        parent = theParent;
    }

    @Override
    public BinarySortTreeNode getLeftChild() {
        return (BinarySortTreeNode)super.getLeftChild();
    }

    @Override
    public BinarySortTreeNode getRightChild() {
        return (BinarySortTreeNode)super.getRightChild();
    }

    public void setParent(BinarySortTreeNode theParent) {
        parent = theParent;
    }

    public BinarySortTreeNode getParent() {
        return parent;
    }

    public childMark getMark() {
        return mark;
    }

    public void setMark(childMark mark) {
        this.mark = mark;
    }
}

