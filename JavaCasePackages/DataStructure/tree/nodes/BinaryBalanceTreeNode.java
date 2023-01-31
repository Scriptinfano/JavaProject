package DataStructure.tree.nodes;

public final class BinaryBalanceTreeNode extends BinarySortTreeNode {
    public BinaryBalanceTreeNode(Integer theValue) {
        super(theValue);
    }

    /**
     * 由于在不平衡子树根节点的右子树的右子树上插入节点导致不平衡，所以将这种调整命名为RR型调整
     */
    public void RR_Rotate() {
        //创建新的节点，值为当前节点的值
        var newNode = new BinaryBalanceTreeNode(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.setLeftChild(getLeftChild());
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.setRightChild(getRightChild().getLeftChild());
        //把当前节点的值换为右子节点的值
        setValue(getRightChild().getValue());
        //将当前节点的左子设为新节点
        setLeftChild(newNode);
        //将当前节点的右子设为右子节点的右子节点
        setRightChild(getRightChild().getRightChild());
    }

    /**
     * 由于在不平衡子树根节点的左子树的左子树上插入节点导致不平衡，所以将这种调整命名为LL型调整
     */
    public void LL_Rotate() {
        //注意某些节点在失去指向它的引用之后，垃圾收集器迟早会消除掉这些节点

        //创建一个新节点，值为当前结点的值
        var newNode = new BinaryBalanceTreeNode(value);
        //将新节点的右子树设为当前节点的右子节点
        newNode.setRightChild(getRightChild());
        //将新节点的左子节点设为当前节点的左子节点的右子节点
        newNode.setLeftChild(getLeftChild().getRightChild());
        //将当前节点的值设为左子节点的值
        setValue(getLeftChild().getValue());
        //将当前节点的左子节点设为当前节点的左子节点的左子节点
        setLeftChild(getLeftChild().getLeftChild());
        //将当前节点的右子节点设为新节点
        setRightChild(newNode);

    }

    /**
     * 由于在不平衡子树根节点的右子树的左子树上插入节点导致不平衡，所以将这种调整命名为RL型调整
     */
    public void RL_Rotate() {

    }

    /**
     * 由于在不平衡子树根节点的左子树的右子树上插入节点导致不平衡，所以将这种调整命名为LR型调整
     */
    public void LR_Rotate() {

    }


    @Override
    public BinaryBalanceTreeNode getLeftChild() {
        return (BinaryBalanceTreeNode) super.getLeftChild();
    }


    @Override
    public BinaryBalanceTreeNode getRightChild() {
        return (BinaryBalanceTreeNode) super.getRightChild();
    }

    @Override
    public BinaryBalanceTreeNode getParent() {
        return (BinaryBalanceTreeNode) super.getParent();
    }

    @Override
    public void setParent(BinarySortTreeNode theParent) {
        super.setParent(theParent);
    }
}
