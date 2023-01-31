package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryBalanceTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;
import arrayutil.ArrayUtil;
import testResources.Person;

public class BinaryBalanceTree extends BinarySortTree {

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
        judgeBalanceAndRotate((BinaryBalanceTreeNode) node);//从插入的节点一直向上遍历父节点，直到找到最小的不平衡子树
    }

    /**
     * 从插入节点依次遍历父节点，查找第一个不平衡的子树
     *
     * @param node 最后插入的节点
     */
    private void judgeBalanceAndRotate(BinaryBalanceTreeNode node) {

        //从插入节点开始向上回溯，不断遍历父节点，然后判断每个节点的平衡性，然后判断需要四种旋转类型的哪一种
        BinaryBalanceTreeNode tempNode = node;
        while (tempNode.getParent() != null || tempNode == root) {
            int heightDifference = tempNode.judgeBalance();
            if (heightDifference > 1) {
                //左子树较高的情况，继续判断应该执行LR旋转还是LL旋转
                var leftRoot = tempNode.getLeftChild();
                if (leftRoot.rightHeight() > leftRoot.leftHeight())
                    tempNode.LR_Rotate();
                else tempNode.LL_Rotate();
                break;
            } else if (heightDifference < -1) {
                //右子树较高的情况，继续判断应该执行RL旋转还是RR旋转
                var rightRoot = tempNode.getRightChild();
                if (rightRoot.leftHeight() > rightRoot.rightHeight())
                    tempNode.RL_Rotate();
                else tempNode.RR_Rotate();
                break;
            }
            tempNode = tempNode.getParent();
            if (tempNode == null) break;
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

}

class TestBalanceTree {
    public static void main(String[] args) {
        Integer[] arr = {10, 8, 17, 15, 19, 16};
        ArrayUtil.showArray(arr);
        BinaryBalanceTree theBalanceTree = new BinaryBalanceTree(arr);
        /*try {
            theBalanceTree.search(57);
        } catch (NodeNotFoundException e) {
            System.out.println(e.getMessage());
        }*/
        Person p = new Person();


    }
}
