package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryBalanceTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;

public class BinaryBalanceTree extends BinarySortTree {
    //TODO 完成二叉平衡树的设计

    /**
     * 按照给定的权值序列，依次将这些节点插入二叉平衡树并时刻保持二叉平衡树的特性
     *
     * @param values 权值序列
     */
    public BinaryBalanceTree(Integer[] values) {
        super();//调用的是基类的空构造器，不做任何事情
        for (Integer value : values) {
            BinaryBalanceTreeNode newNode = new BinaryBalanceTreeNode(value);//二叉平衡树的节点仍然采用二叉排序树的节点，二者基本一致
            insert(newNode);
        }
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param node 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode<Integer> node) {
        super.insert(node);//先按照二叉排序树的方法插入一个节点，然后再判断是否打破的了二叉平衡树的平衡，再执行具体的调整
        //若 右子树高度-左子树高度>1，则应该执行具体的调整，否则什么也不做


    }

    /**
     * 递归地判断每个节点的左右子树高度是否有不平衡的现象出现
     *
     * @param node 节点
     */
    private void judgeBalance(BinaryBalanceTreeNode node) throws RotateMarkException {

        if (node.getLeftChild() == null && node.getRightChild() == null)
            return;
        else {
            if (node.rightHeight() - node.leftHeight() > 1) {
                throw new RotateMarkException(false);//表示需要左旋转
            } else if (node.rightHeight() - node.leftHeight() < 1) {
                throw new RotateMarkException(true);//表示需要右旋转
            } else {
                //左右子树平衡的情况，继续递归判断子树的平衡情况
                judgeBalance(node.getLeftChild());
                judgeBalance(node.getRightChild());
            }
        }
    }

    private class RotateMarkException extends Exception {
        /**
         * 若为true则表示需要右旋转，否则需要左旋转
         */
        private boolean rightOrLeft;

        public RotateMarkException(boolean rightOrLeft) {
            this.rightOrLeft = rightOrLeft;
        }

        public boolean isRightOrLeft() {
            return rightOrLeft;
        }
    }

    /**
     * 搜索节点值为value的节点，并返回该节点引用，若未找到则返回null
     *
     * @param value 要搜索的节点的值为value
     * @return {@link BinaryTreeNode<Integer>} 反回的找到的节点
     */
    @Override
    public BinaryTreeNode<Integer> search(Object value) throws NodeNotFoundException {
        return null;
    }

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(Object value) throws NodeNotFoundException, CollectionEmptyException {

    }
}
