package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryBalanceTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;
import arrayutil.ArrayUtil;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

public class BinaryBalanceTree<T extends Comparable<T>> extends BinarySortTree<T> {

    /**
     * 按照给定的权值序列，依次将这些节点插入二叉平衡树并时刻保持二叉平衡树的特性
     *
     * @param values 权值序列
     */
    public BinaryBalanceTree(T[] values) {
        super();//调用的是基类的空构造器，不做任何事情
        for (T value : values) {
            BinaryBalanceTreeNode<T> newNode = new BinaryBalanceTreeNode<>(value);//二叉平衡树的节点仍然采用二叉排序树的节点，二者基本一致
            insert(newNode);
        }
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param newNode 待插入的节点
     */

    public void insert(BinaryTreeNode<T> newNode) {
        super.insert(newNode);//先按照二叉排序树的方法插入一个节点，然后再判断是否打破的了二叉平衡树的平衡，再执行具体的调整
        judgeBalanceAndRotate((BinaryBalanceTreeNode<T>) newNode);//从插入的节点一直向上遍历父节点，直到找到最小的不平衡子树
    }

    /**
     * 从给定节点开始依次遍历父节点，查找第一个不平衡的子树，在找到不平衡子树的根节点之后，对根节点调用旋转算法对二叉平衡树进行调整
     *
     * @param node 最后插入的节点
     */
    private void judgeBalanceAndRotate(BinaryBalanceTreeNode<T> node) {

        //从插入节点开始向上回溯，不断遍历父节点，然后判断每个节点的平衡性，然后判断需要四种旋转类型的哪一种
        BinaryBalanceTreeNode<T> tempNode = node;
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
    public BinaryBalanceTreeNode<T> search(T value) throws NodeNotFoundException {
        return (BinaryBalanceTreeNode<T>) super.search(value);//搜索功能不涉及对节点的插入和删除，所以步骤和二叉排序树完全一样，所以直接调用父类接口
    }

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 删除值为value的节点
     * @throws NodeNotFoundException 当未找到该节点时抛出该异常
     */
    @Override
    public void delete(T value) throws NodeNotFoundException, CollectionEmptyException {
        //TODO 完成二叉树的删除节点功能
        /*
         * 从平衡二叉树中删除节点也分为两步，第一步完成节点的删除，第二步找到因为删除而导致不满足平衡二叉树要求的子树并对其进行调整*/
        if (isEmpty()) throw new CollectionEmptyException();
        BinaryBalanceTreeNode<T> tempNode = delete((BinaryBalanceTreeNode<T>) root, value);
        if (tempNode == null) throw new NodeNotFoundException(value);
        else {
            root = tempNode;
        }
    }

    /**
     * 删除平衡二叉树的节点的递归函数
     *
     * @param node  要删除的节点在以该节点为根节点的子树中
     * @param value 要删除的节点的权值，如果不存在该权值，则什么也不做
     * @return {@link BinaryBalanceTreeNode}<{@link T}> 返回删除了目标节点并经过调整之后的子树的根节点
     */
    private BinaryBalanceTreeNode<T> delete(BinaryBalanceTreeNode<T> node, T value) {
//TODO 完成二叉平衡树的删除代码测试
        /*if (node == null) return null;//判断当前节点是否为空
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeftChild(delete(node.getLeftChild(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRightChild(delete(node.getRightChild(), value));
        } else {
            var parentNode = node.getParent();
            //找到了待删除的节点
            if (node.getLeftChild() == null && node.getRightChild() != null) {
                //左子树是空，右子树不是空的情况
                if (node.getMark() == BinarySortTreeNode.ChildMark.RIGHT) {
                    parentNode.setRightChild(node.getRightChild());
                    judgeBalanceAndRotate(parentNode.getRightChild());
                } else {
                    parentNode.setLeftChild(node.getRightChild());
                    judgeBalanceAndRotate(parentNode.getLeftChild());
                }
            } else if (node.getLeftChild() != null && node.getRightChild() == null) {
                //右子树是空，左子树不是空的情况
                if (node.getMark() == BinarySortTreeNode.ChildMark.RIGHT) {
                    parentNode.setRightChild(node.getLeftChild());
                    judgeBalanceAndRotate(parentNode.getRightChild());
                } else {
                    parentNode.setLeftChild(node.getLeftChild());
                    judgeBalanceAndRotate(parentNode.getLeftChild());
                }
            } else if (node.getLeftChild() != null && node.getRightChild() != null) {
                //两颗子树都不是空的情况
                int balance = node.leftHeight() - node.rightHeight();
                if (balance == -1) {
                    //该节点的右子树比较高
                } else {
                    //该节点的
                }
            } else {
                //两颗子树都是空的情况
            }

        }*/
        return null;
    }

}


class TestBalanceTree {
    private static ScannerPlus scanner = new ScannerPlus();

    public static void main(String[] args) {
        Integer[] arr = {10, 8, 17, 15, 19, 16};
        ArrayUtil.showArray(arr);
        BinaryBalanceTree<Integer> theBalanceTree = new BinaryBalanceTree<>(arr);

        while (true) {
            System.out.println("输入要查找的数字：");
            int searchNum = scanner.nextInt();
            try {
                theBalanceTree.search(searchNum);
                System.out.println("找到了");
            } catch (NodeNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }
    }

    public static void testPerson() {
        /*Person[] persons = {new Person("小明",12), new Person("小白",43),new Person("校长",41),new Person("诗人",44)};
        System.out.println(Arrays.toString(persons));
        BinaryBalanceTree<Person> theBalanceTree = new BinaryBalanceTree<>(persons);
        ScannerPlus scanner = new ScannerPlus();
        while (true) {
            System.out.println("输入要查找的人的姓名：");
            int searchNum = scanner.nextInt();
            try {
                theBalanceTree.search();
                System.out.println("找到了");
            } catch (NodeNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }*/
    }
}
