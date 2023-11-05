package DataStructure.tree.trees;

import DataStructure.dataStructureInterfaces.Tree;
import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryTreeNode;

import java.util.*;

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
    public final ArrayList<T> preOrderWithoutRecurse() {
        ArrayList<T> resultList = new ArrayList<>();
        if (!isEmpty()) {
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
        }
        return resultList;
    }

    /**
     * 不使用递归方式实现的中序遍历
     *
     * @return {@link List}<{@link T}>返回填满树节点的容器
     */
    @Override
    public final ArrayList<T> inOrderWithoutRecurse() {

        ArrayList<T> resultList = new ArrayList<>();
        if (!isEmpty()) {
            Stack<BinaryTreeNode<T>> treeStack = new Stack<>();
            BinaryTreeNode<T> tempNode = root;

            while (tempNode != null || !treeStack.isEmpty()) {
                while (tempNode != null) {
                    treeStack.push(tempNode);
                    tempNode = tempNode.getLeftChild();
                }
                tempNode = treeStack.pop();
                resultList.add(tempNode.getValue());
                tempNode = tempNode.getRightChild();
            }
        }
        return resultList;
    }

    /**
     * 不使用递归方式实现的后序遍历
     *
     * @return {@link List}<{@link T}>返回填满树节点的容器
     */
    @Override
    public final ArrayList<T> postOrderWithoutRecurse() {

        ArrayList<T> resultList = new ArrayList<>();
        if (!isEmpty()) {
            Stack<BinaryTreeNode<T>> s1 = new Stack<>();
            Stack<BinaryTreeNode<T>> s2 = new Stack<>();
            BinaryTreeNode<T> tempNode = root;
            s1.push(tempNode);
            while (!s1.isEmpty()) {
                tempNode = s1.pop();
                s2.push(tempNode);
                if (tempNode.getLeftChild() != null) s1.push(tempNode.getLeftChild());
                if (tempNode.getRightChild() != null) s1.push(tempNode.getRightChild());

            }
            while (!s2.isEmpty())
                resultList.add(s2.pop().getValue());
        }
        return resultList;
    }

    /**
     * 层次遍历
     *
     * @return {@link List}<{@link T}> 返回填满树节点的容器
     */
    @Override
    public final ArrayList<T> levelOrder() {
        //TODO 完成层次遍历
        return null;
    }

    /**
     * 向树中插入一个新节点，由于各个树的插入算法不一样，所以子类均需要重写该接口
     *
     * @param value 待插入的节点
     */
    @Override
    public abstract void insert(T value);

    public abstract void insert(BinaryTreeNode<T> newNode);

    /**
     * 使用递归的方式计算树中的叶子节点的数目
     *
     * @return int
     */
    @Override
    public final int leafSize() {
        return leafSizeRecursive(root);
    }

    private int leafSizeRecursive(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        if (node.getLeftChild() == null && node.getRightChild() == null) return 1;
        return leafSizeRecursive(node.getLeftChild()) + leafSizeRecursive(node.getRightChild());//这是非叶子节点的情况，需要继续递归
    }

    /**
     * 求总树高
     *
     * @return 总树高
     */
    @Override
    public final int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(BinaryTreeNode<T> node) {
        if (node == null) return 0;
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
     * 删除值为value的节点，若未找到节点则抛出异常，由于各种树删除节点的操作可能不一样，所以所有继承该抽象类的子类均需要实现该方法
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
    public final ArrayList<T> preOrder() {
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
    public final ArrayList<T> inOrder() {
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
    public final ArrayList<T> postOrder() {
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

    @Override
    public final void output() {
        if (root == null) return;
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                BinaryTreeNode<T> node = queue.poll();
                System.out.println(node + " ");
                if (node.getLeftChild() != null) queue.offer(node.getLeftChild());
                if (node.getRightChild() != null) queue.offer(node.getRightChild());
            }
            System.out.println();
        }
    }
}