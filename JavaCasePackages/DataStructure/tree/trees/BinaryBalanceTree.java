package DataStructure.tree.trees;

import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.nodes.BinaryBalanceTreeNode;
import DataStructure.tree.nodes.BinarySortTreeNode;
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
     * @param node 待插入的节点
     */
    @Override
    public void insert(BinaryTreeNode<T> node) {
        super.insert(node);//先按照二叉排序树的方法插入一个节点，然后再判断是否打破的了二叉平衡树的平衡，再执行具体的调整
        judgeBalanceAndRotate((BinaryBalanceTreeNode<T>) node);//从插入的节点一直向上遍历父节点，直到找到最小的不平衡子树
    }

    /**
     * 从插入节点依次遍历父节点，查找第一个不平衡的子树
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
     * @param value 价值
     * @throws NodeNotFoundException 节点没有发现异常
     */
    @Override
    public void delete(T value) throws NodeNotFoundException, CollectionEmptyException {
        //TODO 完成二叉树的删除节点功能
    }

    private BinaryBalanceTreeNode<T> delete(BinaryBalanceTreeNode<T>node,T value){
        if(isEmpty())
            return node;
        if(value.compareTo(node.getValue())<0){
            node.setLeftChild(delete(node.getLeftChild(),value));
        }else if(value.compareTo(node.getValue())>0){
            node.setRightChild(delete(node.getRightChild(),value));
        }else {
            if(node.getLeftChild()==null||node.getRightChild()==null){
                BinaryBalanceTreeNode<T> temp=null;
                //将temp变为不为空的左节点或右节点
                if(temp==node.getLeftChild())
                    temp=node.getRightChild();
                else
                    temp=node.getLeftChild();

                if(temp==null){
                //左右子节点均为空的情况
                temp=node;
                node=null;
                }else
                    node=temp;
            }
        }
    }

}
/*AI代码参考
public class AVLTree {

    private class Node {
        int key, height;
        Node left, right;

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // the key already exists
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return

* */

class TestBalanceTree {
    public static void main(String[] args) {
        Integer[] arr = {10, 8, 17, 15, 19, 16};
        ArrayUtil.showArray(arr);
        BinaryBalanceTree<Integer> theBalanceTree = new BinaryBalanceTree<>(arr);
        ScannerPlus scanner = new ScannerPlus();
        while(true){
            System.out.println("输入要查找的数字：");
            int searchNum =scanner.nextInt();
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
}
