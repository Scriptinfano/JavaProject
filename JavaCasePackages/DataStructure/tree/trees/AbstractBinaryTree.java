package DataStructure.tree.trees;

import DataStructure.dataStructureInterfaces.Tree;
import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class AbstractBinaryTree<T extends Comparable<T>> implements Tree<T> {

    protected BinaryTreeNode<T> root;//树的根节点
    protected Integer treeSize;//树中的节点个数

    public final BinaryTreeNode<T> getRoot() {
        return root;
    }

    public final int getTreeSize() {
        return treeSize;
    }

    /**
     * 判断是否为空树
     *
     * @return boolean
     */
    @Override
    public final boolean isEmpty() {
        return root == null;
    }

    /**
     * 不使用递归方式实现的前序遍历
     *
     * @return {@link List}<{@link BinaryTreeNode<T>}> 返回填满树节点的容器
     */
    @Override
    public final List<T> preOrderWithoutRecurse() {
        List<T> resultList = new Stack<>();
        if (isEmpty()) return resultList;
        Stack<BinaryTreeNode<T>> treeStack = new Stack<>();

        treeStack.push(root);
        while (!treeStack.isEmpty()) {
            BinaryTreeNode<T> tempNode = treeStack.pop();
            if (tempNode != null) {
                resultList.add(tempNode.getValue());
                treeStack.push(tempNode.getRightChild());
                treeStack.push(tempNode.getLeftChild());
            }
        }

        return resultList;
    }

    /**
     * 不使用递归方式实现的中序遍历
     *
     * @return {@link List}<{@link T}>返回填满树节点的容器
     */
    @Override
    public final List<T> inOrderWithoutRecurse() {
        //TODO 完成不使用递归实现的中序遍历
        return null;
    }

    /**
     * 不使用递归方式实现的后序遍历
     *
     * @return {@link List}<{@link T}>返回填满树节点的容器
     */
    @Override
    public final List<T> postOrderWithoutRecurse() {
        //TODO 完成不使用递归实现的后序遍历
        return null;
    }

    /**
     * 层次遍历
     *
     * @return {@link List}<{@link T}> 返回填满树节点的容器
     */
    @Override
    public final List<T> levelOrder() {
        //TODO 完成层次遍历
        return null;
    }

    /**
     * 向树中插入一个新节点，由于各个树的插入算法不一样，所以子类均需要重写该接口
     *
     * @param node 待插入的节点
     */
    @Override
    public abstract void insert(BinaryTreeNode<T> node);

    /**
     * 使用递归的方式计算树中的叶子节点的数目
     *
     * @return int
     */
    @Override
    public final int leafSize() throws CollectionEmptyException {
        if (root == null)
            throw new CollectionEmptyException();
        return 1 + leafSizeRecursive(root.getLeftChild()) + leafSizeRecursive(root.getRightChild());
    }

    private int leafSizeRecursive(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        if (node.getLeftChild() == null && root.getRightChild() == null) return 1;
        return leafSizeRecursive(node.getLeftChild()) + leafSizeRecursive(node.getRightChild());//这是非叶子节点的情况，需要继续递归
    }

    /**
     * 求总树高
     *
     * @return int 总的树高
     */
    @Override
    public final int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(BinaryTreeNode<T> node) {
        if (node == null)
            return 0;
        int i = heightRecursive(node.getLeftChild());
        int j = heightRecursive(node.getRightChild());
        return i > j ? i + 1 : j + 1;
    }

    /**
     * 搜索节点值为value的节点，并返回该节点引用，若未找到则返回null
     *
     * @param value 要搜索的节点的值为value
     * @return {@link BinaryTreeNode<T>} 反回的找到的节点
     */
    @Override
    public abstract BinaryTreeNode<T> search(T value) throws NodeNotFoundException;

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public abstract void delete(T value) throws NodeNotFoundException, CollectionEmptyException;

    /**
     * 递归实现前序遍历 该接口在实例子类中可选择实现
     *
     * @return {@link List}<{@link T}> 返回装着以节点值为元素的容器
     */
    @Override
    public final List<T> preOrder() {
        ArrayList<T> list = new ArrayList<>();
        preOrderRecursive(list, root);
        return list;
    }

    private void preOrderRecursive(List<T> list, BinaryTreeNode<T> node) {
        if (node != null) {
            list.add(node.getValue());
            preOrderRecursive(list, node.getLeftChild());
            preOrderRecursive(list, node.getRightChild());
        }
    }

    /**
     * 递归实现中序遍历 该接口在实例子类中可选择实现
     *
     * @return {@link List}<{@link T}>返回装着以节点值为元素的容器
     */
    @Override
    public final List<T> inOrder() {
        ArrayList<T> list = new ArrayList<>();
        inOrderRecursive(list, root);
        return list;
    }

    private void inOrderRecursive(List<T> list, BinaryTreeNode<T> node) {
        if (node != null) {
            inOrderRecursive(list, node.getLeftChild());
            list.add(node.getValue());
            inOrderRecursive(list, node.getRightChild());
        }
    }

    /**
     * 递归实现后序遍历 该接口在实例子类中可选择实现
     *
     * @return {@link List}<{@link T}>返回装着以节点值为元素的容器
     */
    @Override
    public List<T> postOrder() {
        ArrayList<T> list = new ArrayList<>();
        postOrderRecursive(list, root);
        return list;
    }

    private void postOrderRecursive(List<T> list, BinaryTreeNode<T> node) {
        if (node != null) {
            postOrderRecursive(list, node.getLeftChild());
            postOrderRecursive(list, node.getRightChild());
            list.add(node.getValue());
        }
    }

    /**
     * 比较两棵树是否相同
     *
     * @param obj obj 传入的对象
     * @return boolean 相同则返回true
     */
    @Override
    public final boolean equals(Object obj) {
        //在该对象是继承了抽象树的实例树时，才能比较两者是否相同
        //TODO 完成树的比较操作
        return obj instanceof Tree<?>;

    }
}