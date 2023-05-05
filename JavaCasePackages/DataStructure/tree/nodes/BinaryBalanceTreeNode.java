package DataStructure.tree.nodes;

public class BinaryBalanceTreeNode<T extends Comparable<T>> extends BinarySortTreeNode<T> {
    public BinaryBalanceTreeNode(T theValue) {
        super(theValue);
    }

    /**
     * 由于在不平衡子树根节点的右子树的右子树上插入节点导致不平衡，所以将这种调整命名为RR型调整
     */
    public final void RR_Rotate() {
        var newNode = new BinaryBalanceTreeNode<>(value);
        newNode.setLeftChild(getLeftChild());
        newNode.setRightChild(getRightChild().getLeftChild());
        setValue(getRightChild().getValue());
        setRightChild(getRightChild().getRightChild());
        setLeftChild(newNode);
    }

    /**
     * 由于在不平衡子树根节点的左子树的左子树上插入节点导致不平衡，所以将这种调整命名为LL型调整
     */
    public final void LL_Rotate() {
        var newNode = new BinaryBalanceTreeNode<>(value);
        newNode.setRightChild(getRightChild());
        newNode.setLeftChild(getLeftChild().getRightChild());
        setValue(getLeftChild().getValue());
        setLeftChild(getLeftChild().getLeftChild());
        setRightChild(newNode);

    }

    /**
     * 由于在不平衡子树根节点的右子树的左子树上插入节点导致不平衡，所以将这种调整命名为RL型调整
     */
    public final void RL_Rotate() {
        //对当前节点的右子树进行调整，使其满足RR型的旋转条件，然后对当前节点进行一次RR旋转即可
        getRightChild().LL_Rotate();
        RR_Rotate();
    }

    /**
     * 由于在不平衡子树根节点的左子树的右子树上插入节点导致不平衡，所以将这种调整命名为LR型调整
     */
    public final void LR_Rotate() {
        //对当前节点的左子树进行调整，使其满足LL型的旋转条件，对当前节点进行一次LL旋转即可
        getLeftChild().RR_Rotate();
        LL_Rotate();
    }

    /**
     * 判断节点的平衡性，返回左子树高-右子树高的差值
     *
     * @return int 返回左子树的高度-右子树高度的结果
     */
    public final int judgeBalance() {
        return leftHeight() - rightHeight();
    }


    @Override
    public BinaryBalanceTreeNode<T> getLeftChild() {
        return (BinaryBalanceTreeNode<T>) super.getLeftChild();
    }


    @Override
    public BinaryBalanceTreeNode<T> getRightChild() {
        return (BinaryBalanceTreeNode<T>) super.getRightChild();
    }

    @Override
    public BinaryBalanceTreeNode<T> getParent() {
        return (BinaryBalanceTreeNode<T>) super.getParent();
    }


}
