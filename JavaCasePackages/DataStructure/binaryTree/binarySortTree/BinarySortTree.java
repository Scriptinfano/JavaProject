package binaryTree.binarySortTree;

import arrayutil.ArrayUtil;
import binaryTree.nodes.BinarySortTreeNode;
import myScannerAndPrinter.Iotransformer;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

/**
 * 二叉排序树
 *
 * @author Mingxiang
 */
public class BinarySortTree<T extends Comparable<T>> {
    private BinarySortTreeNode<T> root;

    /**
     * 传入关键码集合，构造二叉排序树
     *
     * @param arr 加勒比海盗
     */
    public BinarySortTree(T[] arr) {
        for (T t : arr) {
            insertNode(t);
        }

    }

    /**
     * 插入节点
     *
     * @param data 要插入节点的数据域的值
     */
    public void insertNode(T data) {
        if (root == null) {
            root = new BinarySortTreeNode<>(null, data);
            root.setMark(BinarySortTreeNode.childMark.NONE);
        } else {
            if (data.compareTo(root.getData()) < 0) {
                insertNodeRecurse(root, root.getLeftChild(), data, false);
            } else
                insertNodeRecurse(root, root.getRightChild(), data, true);
        }

    }

    /**
     * 插入节点的递归函数
     *
     * @param data         数据
     * @param previousNode 当前节点的父节点
     * @param currentNode  当前节点
     * @param rightOrLeft  指示当前节点是之前节点的左孩子还是右孩子，为true时是右孩子，为false时为左孩子
     */
    private void insertNodeRecurse(BinarySortTreeNode<T> previousNode, BinarySortTreeNode<T> currentNode, T data, boolean rightOrLeft) {
        if (currentNode == null) {
            currentNode = new BinarySortTreeNode<>(previousNode, data);
            if (rightOrLeft) {
                previousNode.setRightChild(currentNode);
                currentNode.setMark(BinarySortTreeNode.childMark.RIGHT);
            } else {
                previousNode.setLeftChild(currentNode);
                currentNode.setMark(BinarySortTreeNode.childMark.LEFT);
            }
        } else {
            if (data.compareTo(currentNode.getData()) < 0) {
                insertNodeRecurse(currentNode, currentNode.getLeftChild(), data, false);
            } else
                insertNodeRecurse(currentNode, currentNode.getRightChild(), data, true);
        }
    }

    /**
     * 对二叉排序树执行中序遍历操作
     */
    public void inorder() {
    }

    /**
     * 搜索节点数据域是data的节点，返回该节点
     *
     * @param data 数据
     * @return {@link BinarySortTreeNode}<{@link T}>
     */
    public BinarySortTreeNode<T> searchNode(T data) {
        if (root == null)
            return null;
        else {
            if (data.compareTo(root.getData()) < 0) {
                return searchNodeRecurse(root.getLeftChild(), data);
            } else if (data.compareTo(root.getData()) > 0)
                return searchNodeRecurse(root.getRightChild(), data);
            else return root;
        }
    }

    /**
     * 搜索节点的递归函数
     *
     * @param theNode 节点
     * @param data    数据
     * @return {@link BinarySortTreeNode}<{@link T}>
     */
    private BinarySortTreeNode<T> searchNodeRecurse(BinarySortTreeNode<T> theNode, T data) {
        if (theNode == null)
            return null;//未找到
        else if (data.compareTo(theNode.getData()) < 0)
            return searchNodeRecurse(theNode.getLeftChild(), data);//在左子树中递归查找
        else if (data.compareTo(theNode.getData()) > 0)
            return searchNodeRecurse(theNode.getRightChild(), data);//在右子树中递归查找
        else return theNode;//找到了

    }

    public void deleteNode(T data) throws IllegalArgumentException {
        if (root != null) {
            BinarySortTreeNode<T> theNode = searchNode(data);
            if (theNode == null)
                return;//没有找到待删除的节点
            BinarySortTreeNode<T> theParentNode = theNode.getParent();

            if (theNode.getLeftChild() != null && theNode.getRightChild() != null) {
                //待删除的节点有左右子树的情况
                /*
                 * 处理方法：以其左子树中最大值节点（左子树最右下节点）或右子树中最小值节点（右子树最左下节点）替代之，然后再删除该节点*/
                //此处有两种选择，可以选择删除左子树中最大值节点也可以删除右子树中最小值节点，这里以删除左子树中最大值节点为例
                BinarySortTreeNode<T> theLargestNode = deleteNode_findLargestNodeInLeftTree(root);
                theNode.setData(theLargestNode.getData());
                BinarySortTreeNode<T> leftChildOfLargestNode = theLargestNode.getLeftChild();
                theLargestNode.getParent().setRightChild(leftChildOfLargestNode);
                leftChildOfLargestNode.setMark(BinarySortTreeNode.childMark.RIGHT);
            } else if (theNode.getLeftChild() == null && theNode.getRightChild() == null) {
                //待删除的节点是叶子节点或是根节点
                if (theNode.getMark() == BinarySortTreeNode.childMark.NONE)
                    //该节点就是根节点的情况
                    root = null;
                else if (theNode.getMark() == BinarySortTreeNode.childMark.RIGHT)
                    theParentNode.setRightChild(null);
                else
                    theParentNode.setLeftChild(null);
            } else {
                //待删除的节点只有一颗子树
                if (theNode.getRightChild() != null) {
                    //该节点只有右子树的情况
                    if (theNode.getMark() == BinarySortTreeNode.childMark.RIGHT) {
                        //该节点是某节点的右孩子
                        theParentNode.setRightChild(theNode.getRightChild());
                    } else if (theNode.getMark() == BinarySortTreeNode.childMark.LEFT) {
                        //该节点是某节点的左孩子
                        theParentNode.setLeftChild(theNode.getRightChild());
                        theNode.getRightChild().setMark(BinarySortTreeNode.childMark.LEFT);
                    } else {
                        //该节点是根节点且该节点只有一颗右子树
                        root = root.getRightChild();
                        root.setMark(BinarySortTreeNode.childMark.NONE);
                    }
                } else {
                    //该节点只有左子树的情况
                    if (theNode.getMark() == BinarySortTreeNode.childMark.RIGHT) {
                        //该节点是某节点的右孩子
                        theParentNode.setRightChild(theNode.getLeftChild());
                        theNode.getLeftChild().setMark(BinarySortTreeNode.childMark.RIGHT);
                    } else if (theNode.getMark() == BinarySortTreeNode.childMark.LEFT) {
                        //该节点是某节点的左孩子
                        theParentNode.setLeftChild(theNode.getLeftChild());
                    } else {
                        //该节点是根节点且该节点只有一颗左子树
                        root = root.getLeftChild();
                        root.setMark(BinarySortTreeNode.childMark.NONE);
                    }

                }
            }
        } else {
            throw new IllegalArgumentException("该二叉排序树是空树，无法执行任何操作");
        }
    }

    /**
     * 在theNode节点的左子树中找到最大值节点，并返回该节点
     *
     * @param theNode 在以该节点为根节点的左子树中寻找最大值节点
     * @return {@link BinarySortTreeNode}<{@link T}> 返回的最大值节点
     */
    private BinarySortTreeNode<T> deleteNode_findLargestNodeInLeftTree(BinarySortTreeNode<T> theNode) {
        theNode = theNode.getLeftChild();
        while (theNode.getRightChild() != null)
            theNode = theNode.getRightChild();
        return theNode;
    }

    /**
     * 在theNode节点的右子树中找到最小值节点，并返回该节点
     *
     * @param theNode 在以该节点为根节点的右子树中寻找最小值节点
     * @return {@link BinarySortTreeNode}<{@link T}> 返回的最小值节点
     */
    private BinarySortTreeNode<T> deleteNode_findSmallestNodeInRightTree(BinarySortTreeNode<T> theNode) {
        theNode = theNode.getRightChild();
        while (theNode.getLeftChild() != null) {
            theNode = theNode.getLeftChild();
        }
        return theNode;
    }


}

class TestBinarySortTree<T> {
    public static void main(String[] args) {
/*
        Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 100);
*/
        Integer[] arr = {34, 21, 99, 9, 57, 76, 46, 61, 28, 50};
        ArrayUtil.showArray(arr);
        BinarySortTree<Integer> theSortTree = new BinarySortTree<>(arr);
        //testSearch(theSortTree);
        testDelete(theSortTree);
    }

    private static void testSearch(BinarySortTree<Integer> theSortTree) {
        while (true) {
            Iotransformer.printer.print("输入要查找的数字：");
            int theNum = Iotransformer.scanner.nextInt();
            BinarySortTreeNode<Integer> theNode = theSortTree.searchNode(theNum);
            if (theNode != null) {
                Iotransformer.printer.println("找到了位于" + theNode + "的节点");
            } else {
                Iotransformer.printer.println("没找到");
            }
            try {
                Iotransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                break;
            }
        }
        ScannerPlus.pause();
    }

    //TODO 测试删除功能未完成
    private static void testDelete(BinarySortTree<Integer> theSortTree) {
        while (true) {
            Iotransformer.printer.println("输入要删除的数字");
            int theNum = Iotransformer.scanner.nextInt();
            if (theSortTree.searchNode(theNum) != null) {
                //找到了该节点，可以删除
                theSortTree.deleteNode(theNum);
            } else {
                Iotransformer.printer.println("没有找到节点，无法删除");
            }
            try {
                Iotransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                break;
            }

        }
    }
}
