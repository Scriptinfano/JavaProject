package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryBalanceTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;
import arrayutil.ArrayUtil;

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
        try {
            judgeBalance((BinaryBalanceTreeNode) root);
        } catch (RotateMarkException e) {
            //在最小的不平衡子树上执行旋转操作
            if (e.getType() == RotateMarkException.RotateType.LL) {
                e.getUnbalancedNode().LL_Rotate();
            } else if (e.getType() == RotateMarkException.RotateType.RR) {
                e.getUnbalancedNode().RR_Rotate();
            } else if (e.getType() == RotateMarkException.RotateType.LR) {
                e.getUnbalancedNode().LR_Rotate();
            } else {
                e.getUnbalancedNode().RL_Rotate();
            }
        }


    }

    /**
     * 递归地判断每个节点的左右子树高度是否有不平衡的现象出现，并抛出一个异常指示出需要左旋转还是右旋转来调整二叉树使其满足平衡二叉树的要求
     *
     * @param node 节点
     */
    private void judgeBalance(BinaryBalanceTreeNode node) throws RotateMarkException {

        if (node != null && (node.getLeftChild() != null || node.getRightChild() != null)) {
            if (node.rightHeight() - node.leftHeight() > 1) {
                //TODO 进一步判断旋转的类型，此处是右子树比左子树高
            } else if (node.leftHeight() - node.rightHeight() > 1) {
                //TODO 进一步判断旋转的类型，此处是左子树比右子树高
            } else {
                //左右子树平衡的情况，继续递归判断子树的平衡情况
                judgeBalance(node.getLeftChild());
                judgeBalance(node.getRightChild());
            }
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
        //TODO 完成二叉平衡树的搜索功能
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
//TODO 完成二叉树的删除节点功能
    }

    private static class RotateMarkException extends Exception {
        /**
         * 旋转类型
         */
        private final RotateType type;

        /**
         * 构造器
         *
         * @param theType 指定该最小不平衡子树应该执行的旋转类型
         * @param theNode 指定的最小的不平衡子树的根节点
         */
        public RotateMarkException(RotateType theType, BinaryBalanceTreeNode theNode) {
            this.type = theType;
            unbalancedNode = theNode;
        }

        /**
         * 得到当前最小不平衡节点的旋转类型
         *
         * @return {@link RotateType}
         */
        public RotateType getType() {
            return type;
        }

        /**
         * 最小的不平衡子树的根节点
         */
        private final BinaryBalanceTreeNode unbalancedNode;

        /**
         * 得到该异常包装的最小不平衡节点
         *
         * @return {@link BinaryBalanceTreeNode}
         */
        public BinaryBalanceTreeNode getUnbalancedNode() {
            return unbalancedNode;
        }

        /**
         * 旋转类型枚举常量
         */
        private static enum RotateType {
            RR, LL, LR, RL
        }

    }
}

class TestBalanceTree {
    public static void main(String[] args) {
        Integer[] arr = {34, 21, 99, 9, 57, 76, 46, 61, 28, 50};
        ArrayUtil.showArray(arr);
        BinaryBalanceTree theBalanceTree = new BinaryBalanceTree(arr);
        try {
            theBalanceTree.search(57);
        } catch (NodeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
