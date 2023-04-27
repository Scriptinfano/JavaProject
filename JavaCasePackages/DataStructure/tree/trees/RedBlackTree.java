package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinarySortTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;
import DataStructure.tree.nodes.RedBlackTreeNode;

/**
 * 红黑树<br/>
 * 红黑规则：
 * 1、每个节点要么是红色要么是灰色
 * 2、根节点是黑色
 * 3、所有叶节点均为黑色，这些叶节点不存储数据
 * 4、如果一个节点是红色，则其子节点一定是黑色，两个红色节点不能连接在一起
 * 5、对每一个节点，从该节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色节点
 * <p>
 * 添加节点时，默认添加红色节点则效率更高
 *
 * @author Mingxiang
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySortTree<T> {
    /**
     * 传入红黑树节点值的数组，按照红黑规则构造红黑树
     *
     * @param values 要插入的节点的值的数组
     */
    public RedBlackTree(T[] values) {
        super();//调用的是基类的空构造器，不做任何事情
        for (T value : values) {

            insert(value);
        }
    }

    /**
     * 空构造器
     */
    public RedBlackTree() {
        super();
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param newNode 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode<T> newNode) {
        super.insert(newNode);//红黑树的插入是先按照二叉排序树的规则将节点放入到指定的位置上，然后根据红黑规则进行调整，所以此处先让父类排序树完成第一步操作
        adjust((RedBlackTreeNode<T>) newNode);

    }

    private void adjust(RedBlackTreeNode<T> node) {
        if (isEmpty()) {
            node.setColor(RedBlackTreeNode.Color.BLACK);//红黑规则第一条：根节点必须是黑色
            node.generateNilNode();//为根节点生成Nil节点
        } else {
            if (node.getParent().getColor() == RedBlackTreeNode.Color.RED) {
                //父节点是红色的情况
                RedBlackTreeNode<T> grandFatherNode = node.getParent().getParent();
                RedBlackTreeNode<T> fatherNode = node.getParent();

                //根据具体情况取得叔叔节点的引用
                RedBlackTreeNode<T> uncleNode;
                if (node.getParent().getMark() == BinarySortTreeNode.ChildMark.LEFT)
                    uncleNode = grandFatherNode.getRightChild();
                else
                    uncleNode = grandFatherNode.getLeftChild();

                if (uncleNode.getColor() == RedBlackTreeNode.Color.RED) {
                    //叔叔节点是红色的情况
                    fatherNode.setColor(RedBlackTreeNode.Color.BLACK);
                    uncleNode.setColor(RedBlackTreeNode.Color.BLACK);
                    if (grandFatherNode != root) {
                        grandFatherNode.setColor(RedBlackTreeNode.Color.RED);
                        adjust(grandFatherNode);
                    }


                } else if (uncleNode.getColor() == RedBlackTreeNode.Color.BLACK && node.getMark() == BinarySortTreeNode.ChildMark.RIGHT) {
                    //叔叔节点是黑色且当前节点是父节点的右孩子的情况
                    //TODO 将父节点作为当前节点并左旋，再进行判断是什么意思
                    fatherNode.leftRotate();
                } else {
                    //叔叔节点是黑色且当前节点是父节点的左孩子的情况
                    fatherNode.setColor(RedBlackTreeNode.Color.BLACK);
                    grandFatherNode.setColor(RedBlackTreeNode.Color.RED);
                    grandFatherNode.rightRotate();
                }

            }
            //如果父节点是黑色，则什么都不做
        }
    }


    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 要删除的节点的值为value
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(T value) throws NodeNotFoundException, CollectionEmptyException {
        super.delete(value);
    }


}

