package DataStructure.dataStructureInterfaces;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.MyTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;

import java.util.List;

/**
 * 二叉树类应该有的接口，统一树类操作
 *
 * @author Mingxiang
 */
public interface Tree<T extends Comparable<T>> {
    /**
     * 不使用递归方式实现的前序遍历
     * @return {@link List}<{@link T}> 返回以节点的值作为元素的List容器
     */
    List<T> preOrderWithoutRecurse();

    /**
     * 不使用递归方式实现的中序遍历
     *
     * @return {@link List}<{@link T}> 返回以节点的值作为元素的List容器
     */
    List<T> inOrderWithoutRecurse();

    /**
     * 不使用递归方式实现的后序遍历
     *
     * @return {@link List}<{@link T}> 返回以节点的值作为元素的List容器
     */
    List<T> postOrderWithoutRecurse();

    /**
     * 层次遍历
     *
     * @return {@link List}<{@link T}> 返回以节点的值作为元素的List容器
     */
    List<T> levelOrder();

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param node 待插入的节点
     */
    void insert(BinaryTreeNode<T> node);

    /**
     * 使用递归的方式计算树中的叶子节点的数目
     *
     * @return int
     */
    int leafSize() throws CollectionEmptyException;

    /**
     * 求总树高
     *
     * @return int 总的树高
     */
    int height();

    /**
     * 搜索节点值为value的节点，并返回该节点引用，若未找到则返回null
     *
     * @param value 要搜索的节点的值为value
     * @return {@link BinaryTreeNode<T>} 反回的找到的节点
     */
    MyTreeNode<T> search(T value) throws NodeNotFoundException;

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    void delete(T value) throws NodeNotFoundException, CollectionEmptyException;

    /**
     * 判断是否为空树
     *
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 递归实现前序遍历
     *
     * @return {@link List}<{@link T}> 返回装着以节点值为元素的容器
     */
    List<T> preOrder();

    /**
     * 递归实现中序遍历
     *
     * @return {@link List}<{@link T}>返回装着以节点值为元素的容器
     */
    List<T> inOrder();

    /**
     * 递归实现后序遍历
     *
     * @return {@link List}<{@link T}>返回装着以节点值为元素的容器
     */
    List<T> postOrder();

    boolean equals(Object obj);

    void output();


}
