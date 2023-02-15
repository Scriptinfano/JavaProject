package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinarySortTreeNode;
import DataStructure.tree.nodes.BinaryTreeNode;
import arrayutil.ArrayUtil;
import myScannerAndPrinter.IOTransformer;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;
import org.jetbrains.annotations.NotNull;

/**
 * 二叉排序树
 *
 * @author Mingxiang
 */
public class BinarySortTree extends AbstractTree<Integer> {


    /**
     * 传入关键码集合，构造二叉排序树
     *
     * @param arr 加勒比海盗
     */
    public BinarySortTree(Integer[] arr) {
        for (Integer value : arr) {
            BinarySortTreeNode newNode = new BinarySortTreeNode(null, value);
            insert(newNode);
        }

    }

    /**
     * 这是一个给子类使用的空构造器
     */
    protected BinarySortTree() {
    }

    /**
     * 向树中插入一个新节点，若该树有固定的插入规则，则按照该规则插入；若该树没有固定的插入规则，则需要另写方法指定插入位置，然后将该参数传入另一个接口
     *
     * @param node 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode<Integer> node) {
        if (root == null) {
            root = node;
        } else {
            if (node.getValue().compareTo(root.getValue()) < 0) {
                insertNodeRecurse((BinarySortTreeNode) root, ((BinarySortTreeNode) root).getLeftChild(), (BinarySortTreeNode) node, false);
            } else
                insertNodeRecurse((BinarySortTreeNode) root, ((BinarySortTreeNode) root).getRightChild(), (BinarySortTreeNode) node, true);
        }

    }

    /**
     * 插入节点的递归函数
     *
     * @param previousNode 当前节点的父节点
     * @param currentNode  当前节点
     * @param insertNode   要插入的节点
     * @param rightOrLeft  指示当前节点是之前节点的左孩子还是右孩子，为true时是右孩子，为false时为左孩子
     */
    private void insertNodeRecurse(BinarySortTreeNode previousNode, BinarySortTreeNode currentNode, BinarySortTreeNode insertNode, boolean rightOrLeft) {
        if (currentNode == null) {
            if (rightOrLeft) {
                previousNode.setRightChild(insertNode);
            } else {
                previousNode.setLeftChild(insertNode);
            }
        } else {
            if (insertNode.getValue() < currentNode.getValue()) {
                insertNodeRecurse(currentNode, currentNode.getLeftChild(), insertNode, false);
            } else
                insertNodeRecurse(currentNode, currentNode.getRightChild(), insertNode, true);
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
        if (isEmpty())
            return null;
        else {
            if (((Integer) value).compareTo(root.getValue()) < 0) {
                return searchNodeRecurse((BinarySortTreeNode) root.getLeftChild(), (Integer) value);
            } else if (((Integer) value).compareTo(root.getValue()) > 0)
                return searchNodeRecurse((BinarySortTreeNode) root.getRightChild(), (Integer) value);
            else return root;
        }
    }

    /**
     * 搜索节点的递归函数
     *
     * @param theNode 节点
     * @param data    数据
     * @return {@link BinarySortTreeNode} 返回搜索到的节点的引用
     */
    private BinarySortTreeNode searchNodeRecurse(BinarySortTreeNode theNode, Integer data) throws NodeNotFoundException {
        if (theNode == null)
            throw new NodeNotFoundException(data);
        else if (data < theNode.getValue())
            return searchNodeRecurse(theNode.getLeftChild(), data);//在左子树中递归查找
        else if (data > theNode.getValue())
            return searchNodeRecurse(theNode.getRightChild(), data);//在右子树中递归查找
        else return theNode;//找到了

    }

    /**
     * 删除值为value的节点，若未找到节点则抛出异常
     *
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(Object value) throws NodeNotFoundException, CollectionEmptyException {
        if (!isEmpty()) {
            //在树不为空的时候才能执行删除操作1
            BinarySortTreeNode theNode = (BinarySortTreeNode) search(value);//先在树中查找待删除节点
            if (theNode == null)
                throw new NodeNotFoundException(value);//没有找到待删除的节点，抛出没有找到节点的异常
            BinarySortTreeNode theParentNode = theNode.getParent();//得到待删除节点的父节点

            if (theNode.getLeftChild() != null && theNode.getRightChild() != null) {
                /*
                待删除的节点有左右子树的情况。处理方法：以其左子树中最大值节点（左子树最右下节点）或右子树中最小值节点（右子树最左下节点）替代之，然后再删除该节点
                此处有两种选择，可以选择删除左子树中最大值节点也可以删除右子树中最小值节点，这里以删除左子树中最大值节点为例
                */
                BinarySortTreeNode theLargestNode = deleteNode_findLargestNodeInLeftTree(theNode);//找到左子树中的右下节点
                theNode.setValue(theLargestNode.getValue());
                //此时只需删除theLargestNode这个叶子节点即可
                if (theLargestNode.getMark() == BinarySortTreeNode.childMark.LEFT) {
                    theLargestNode.getParent().setLeftChild(null);
                    return;
                }
                if (theLargestNode.getMark() == BinarySortTreeNode.childMark.RIGHT)
                    theLargestNode.getParent().setRightChild(null);
            } else if (theNode.getLeftChild() == null && theNode.getRightChild() == null) {
                //待删除的节点是叶子节点或是没有左右子树的根节点
                if (theNode.getMark() == BinarySortTreeNode.childMark.NONE)
                    //该节点就是根节点的情况
                    root = null;
                else if (theNode.getMark() == BinarySortTreeNode.childMark.RIGHT)
                    //该叶子节点是右子节点
                    theParentNode.setRightChild(null);
                else
                    //该叶子节点是左子节点
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
                    } else {
                        //该节点是根节点且该节点只有一颗右子树
                        root = root.getRightChild();
                    }
                } else {
                    //该节点只有左子树的情况
                    if (theNode.getMark() == BinarySortTreeNode.childMark.RIGHT) {
                        //该节点是某节点的右孩子
                        theParentNode.setRightChild(theNode.getLeftChild());

                    } else if (theNode.getMark() == BinarySortTreeNode.childMark.LEFT) {
                        //该节点是某节点的左孩子
                        theParentNode.setLeftChild(theNode.getLeftChild());
                    } else {
                        //该节点是根节点且该节点只有一颗左子树
                        root = root.getLeftChild();
                    }

                }
            }
        } else {
            throw new CollectionEmptyException("该二叉排序树是空树，无法执行任何操作");
        }
    }

    /**
     * 在theNode节点的左子树中找到最大值节点，并返回该节点
     *
     * @param theNode 在以该节点为根节点的左子树中寻找最大值节点
     * @return {@link BinarySortTreeNode} 返回的最大值节点
     */
    private BinarySortTreeNode deleteNode_findLargestNodeInLeftTree(BinarySortTreeNode theNode) {
        theNode = theNode.getLeftChild();
        while (theNode.getRightChild() != null)
            theNode = theNode.getRightChild();
        return theNode;
    }

    /**
     * 在theNode节点的右子树中找到最小值节点，并返回该节点
     *
     * @param theNode 在以该节点为根节点的右子树中寻找最小值节点
     * @return {@link BinarySortTreeNode} 返回的最小值节点
     */
    private BinarySortTreeNode deleteNode_findSmallestNodeInRightTree(BinarySortTreeNode theNode) {
        theNode = theNode.getRightChild();
        while (theNode.getLeftChild() != null) {
            theNode = theNode.getLeftChild();
        }
        return theNode;
    }


}

class TestBinarySortTree {
    public static void main(String[] args) {

        //Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 100);

        Integer[] arr = {34, 21, 99, 9, 57, 76, 46, 61, 28, 50};
        ArrayUtil.showArray(arr);
        BinarySortTree theSortTree = new BinarySortTree(arr);
        //testSearch(theSortTree);
        testDelete(theSortTree);
    }

    private static void testSearch(@NotNull BinarySortTree theSortTree) {
        while (true) {
            IOTransformer.printer.print("输入要查找的数字：");
            int theNum = IOTransformer.scanner.nextInt();
            BinarySortTreeNode theNode = null;
            try {
                theNode = (BinarySortTreeNode) theSortTree.search(theNum);
                IOTransformer.printer.println("找到了位于" + theNode + "的节点");
            } catch (NodeNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                IOTransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                break;
            }
        }
        ScannerPlus.pause();
    }

    private static void testDelete(BinarySortTree theSortTree) {
        while (true) {
            IOTransformer.printer.println("输入要删除的数字");
            int theNum = IOTransformer.scanner.nextInt();
            try {
                theSortTree.delete(theNum);
            } catch (NodeNotFoundException e) {
                System.out.println(e.getMessage());//未找到要删除的节点
            } catch (CollectionEmptyException e) {
                System.out.println(e.getMessage());
                return;//因为已经是空树了，没有元素可以删除了，直接返回
            }
            try {
                IOTransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                return;
            }

        }
    }
}
