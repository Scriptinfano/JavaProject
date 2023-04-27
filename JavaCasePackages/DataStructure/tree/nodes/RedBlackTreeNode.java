package DataStructure.tree.nodes;

/**
 * 红黑树节点，继承自二叉平衡树的节点，因为在根据红黑规则调整红黑树时，要用到二叉平衡树节点的旋转代码
 *
 * @author Mingxiang
 */
public class RedBlackTreeNode<T extends Comparable<T>> extends BinaryBalanceTreeNode<T> {

    private Color color;

    /**
     * 仅初始化节点值的构造器，其parent默认初始化为null
     * 红黑树节点默认都是红色，只要在插入到树中的时候才会根据具体情况调整颜色
     * 该构造函数尚未初始化父节点指针，需要在后续插入到树中的操作之后指定父节点
     *
     * @param theData 节点的值
     */
    public RedBlackTreeNode(T theData) {
        super(theData);
        color = Color.RED;//一般红黑树的节点默认是红色，这样插入的效率会更高
    }

    /**
     * 为该节点生成Nil节点，当该节点的左孩子或右孩子为空时，为其对应位置生成Nil节点
     */
    public void generateNilNode() {
        if (leftChild == null) {
            leftChild = new RedBlackTreeNode<>(null);
            ((RedBlackTreeNode<T>) leftChild).setColor(Color.BLACK);
            ((RedBlackTreeNode<T>) leftChild).setParent(this);
            ((RedBlackTreeNode<T>) leftChild).setMark(ChildMark.LEFT);
        }
        if (rightChild == null) {
            rightChild = new RedBlackTreeNode<>(null);
            ((RedBlackTreeNode<T>) rightChild).setColor(Color.BLACK);
            ((RedBlackTreeNode<T>) rightChild).setParent(this);
            ((RedBlackTreeNode<T>) rightChild).setMark(ChildMark.RIGHT);
        }

    }

    /**
     * 取得红黑树节点的颜色
     *
     * @return {@link Color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * 设置红黑树节点的颜色
     *
     * @param color 要设置的颜色，
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public RedBlackTreeNode<T> getLeftChild() {
        return (RedBlackTreeNode<T>) super.getLeftChild();
    }

    @Override
    public RedBlackTreeNode<T> getParent() {
        return (RedBlackTreeNode<T>) super.getParent();
    }

    @Override
    public RedBlackTreeNode<T> getRightChild() {
        return (RedBlackTreeNode<T>) super.getRightChild();
    }

    /**
     * 以该红黑树节点为支点，进行右旋转
     */
    public void rightRotate() {
        LL_Rotate();
        //在使用平衡二叉树旋转代码的时候Nil节点已经被正确处理了，此处无需再生成Nil节点
    }

    /**
     * 以该红黑树节点为支点，进行左旋转
     */
    public void leftRotate() {
        RR_Rotate();
        //在使用平衡二叉树旋转代码的时候Nil节点已经被正确处理了，此处无需再生成Nil节点
    }

    /**
     * 红黑树的节点的颜色标签
     *
     * @author Mingxiang
     */
    public enum Color {
        BLACK,
        RED
    }
}
