package DataStructure.tree.nodes;

public class BinaryBalanceTreeNode extends BinarySortTreeNode {
    public BinaryBalanceTreeNode(Integer theValue) {
        super(null, theValue);
    }

    public void leftRotate() {
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
