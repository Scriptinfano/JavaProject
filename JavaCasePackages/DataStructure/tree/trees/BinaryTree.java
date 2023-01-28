package DataStructure.tree.trees;

import DataStructure.dataStructureInterfaces.Tree;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryTreeNode;

import java.util.List;

public class BinaryTree implements Tree {
    /**
     * 不使用递归方式实现的前序遍历
     *
     * @return {@link List}<{@link BinaryTreeNode}> 返回填满树节点的容器
     */
    @Override
    public List<? extends BinaryTreeNode> preOrderWithoutRecurse() {
        return null;
    }

    /**
     * 不使用递归方式实现的中序遍历
     *
     * @return {@link List}<{@link ?} {@link extends} {@link BinaryTreeNode}> 返回填满树节点的容器
     */
    @Override
    public List<? extends BinaryTreeNode> inOrderWithoutRecurse() {
        return null;
    }

    /**
     * 不使用递归方式实现的后序遍历
     *
     * @return {@link List}<{@link ?} {@link extends} {@link BinaryTreeNode}> 返回填满树节点的容器
     */
    @Override
    public List<? extends BinaryTreeNode> postOrderWithoutRecurse() {
        return null;
    }

    /**
     * 层次遍历
     *
     * @return {@link List}<{@link ?} {@link extends} {@link BinaryTreeNode}>返回填满树节点的容器
     */
    @Override
    public List<? extends BinaryTreeNode> levelOrder() {
        return null;
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param node 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode node) {

    }

    /**
     * 使用递归的方式计算树中的叶子节点的数目
     *
     * @return int
     */
    @Override
    public int leafSize() {
        return 0;
    }

    /**
     * 求总树高
     *
     * @return int 总的树高
     */
    @Override
    public int height() {
        return 0;
    }

    /**
     * 搜索节点值为value的节点，并返回该节点引用，若未找到则返回null
     *
     * @param value 要搜索的节点的值为value
     * @return {@link BinaryTreeNode} 反回的找到的节点
     */
    @Override
    public BinaryTreeNode search(Object value) {
        return null;
    }

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(Object value) throws NodeNotFoundException {

    }
}
