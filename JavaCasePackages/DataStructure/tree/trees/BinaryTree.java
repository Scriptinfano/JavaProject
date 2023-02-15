package DataStructure.tree.trees;

import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryTreeNode;

/**
 * 最基本的二叉树
 *
 * @author Mingxiang
 */
public class BinaryTree<T extends Comparable<T>> extends AbstractBinaryTree<T> {
    //TODO 完成基本二叉树的设计

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param node 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode<T> node) {

    }

    /**
     * 搜索节点值为value的节点，并返回该节点引用，若未找到则返回null
     *
     * @param value 要搜索的节点的值为value
     * @return {@link BinaryTreeNode<T>} 反回的找到的节点
     */
    @Override
    public BinaryTreeNode<T> search(T value) {
        return null;
    }

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(T value) throws NodeNotFoundException {

    }
}

class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
    }
}
