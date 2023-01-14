package DataStructure.binaryTree.nodes;

public class ClueTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {
    private boolean leftTag;//在线索化二叉树时指示左指针指向左孩子还是前驱，若为true指向前驱
    private boolean rightTag;//在线索化二叉树时指示右指针指向右孩子还是后驱，若为true指向后继

    public ClueTreeNode() {
        super();
        leftTag = rightTag = false;
    }

    /**
     * 以指定的权值初始化二叉树节点
     *
     * @param theElement 元素
     */
    public ClueTreeNode(T theElement) {
        super(theElement);
        leftTag = rightTag = false;
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
