package binaryTree.binarySortTree;

import arrayutil.ArrayUtil;
import binaryTree.nodes.BinaryTreeNode;
import myScannerAndPrinter.Iotransformer;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

/**
 * 二叉排序树
 *
 * @author Mingxiang
 */
public class BinarySortTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

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
     * @param data 要插入节点的数据域的指
     */
    public void insertNode(T data) {
        if (root == null) {
            root = new BinaryTreeNode<T>(data);
        } else {
            if (data.compareTo(root.getElement()) < 0) {
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
    private void insertNodeRecurse(BinaryTreeNode<T> previousNode, BinaryTreeNode<T> currentNode, T data, boolean rightOrLeft) {
        if (currentNode == null) {
            currentNode = new BinaryTreeNode<T>(data);
            if (rightOrLeft)
                previousNode.setRightChild(currentNode);
            else previousNode.setLeftChild(currentNode);
        } else {
            if (data.compareTo(currentNode.getElement()) < 0) {
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
     * @param data 要搜索的数据
     * @return {@link BinaryTreeNode}<{@link T}> 返回的已经找到的节点
     */
    public BinaryTreeNode<T> searchNode(T data) {
        if (root == null)
            return null;
        else {
            if (data.compareTo(root.getElement()) < 0) {
                return searchNodeRecurse(root.getLeftChild(), data);
            } else if (data.compareTo(root.getElement()) > 0)
                return searchNodeRecurse(root.getRightChild(), data);
            else return root;
        }
    }

    private BinaryTreeNode<T> searchNodeRecurse(BinaryTreeNode<T> theNode, T data) {
        if (theNode == null)
            return null;//未找到
        else if (data.compareTo(theNode.getElement()) < 0)
            return searchNodeRecurse(theNode.getLeftChild(), data);//在左子树中递归查找
        else if (data.compareTo(theNode.getElement()) > 0)
            return searchNodeRecurse(theNode.getRightChild(), data);//在右子树中递归查找
        else return theNode;//找到了

    }

    public void deleteNode(T data){
      if(root != null){
          BinaryTreeNode<T>theNode=searchNode(data);
          if(theNode.getLeftChild()!=null&&theNode.getRightChild() != null){
              //待删除的节点有左右子树的情况
          } else if (theNode.getLeftChild()==null&&theNode.getRightChild() == null) {
              //待删除的节点是叶子节点
          }else {
              if(theNode.getRightChild() != null){
                  //该节点只有右子树的情况
              }else {
                  //该节点只有左子树的情况
              }
          }
      }
    }




}

class TestBinarySortTree{
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArray(5, 1, 100);
        ArrayUtil.showArray(arr);
        BinarySortTree<Integer> theSortTree = new BinarySortTree<>(arr);
        while (true) {
            Iotransformer.printer.print("输入要查找的数字：");
            int theNum = Iotransformer.scanner.nextInt();
            BinaryTreeNode<Integer> theNode = theSortTree.searchNode(theNum);
            if (theNode != null) {
                Iotransformer.printer.println("找到了位于" + theNode + "的节点");
            } else {
                Iotransformer.printer.println("没找到");
            }
            try {
                Iotransformer.scanner.noMoreScan();//询问用户是否还需要输入
            }catch (NoMoreScanException e){
                break;
            }
        }
        ScannerPlus.pause();

    }
}
