package DataStructure.linklist;

import DataStructure.linklist.queueByLinkList.Node;

import java.util.ArrayList;

public class LinkList<T> {
    private final Node<T> head;

    public LinkList() {
        head = new Node<>(null);
    }

    public LinkList(T[] arr) {
        Node<T> newNode = new Node<>(arr[0]);
        Node<T> headNode = new Node<>(null);
        headNode.setNext(newNode);
        Node<T> pointer = newNode;
        for (int i = 1; i < arr.length; i++) {
            pointer.setNext(new Node<>(arr[i]));
            pointer = pointer.getNext();
        }
        head = headNode;
    }

    /**
     * 头插法插入新节点
     *
     * @param element 新节点的值
     */
    public void insertHead(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.setNext(head.getNext());
        head.setNext(newNode);
    }

    /**
     * 尾插法插入新节点
     *
     * @param element 新节点的值
     */
    public void insertRear(T element) {
        Node<T> newNode = new Node<>(element);
        Node<T> pointer = head;
        while (pointer.getNext() != null)
            pointer = pointer.getNext();
        pointer.setNext(newNode);
    }

    public void setHead(Node<T> head) {
        this.head.setNext(head);
    }

    public void output() {
        Node<T> pointer = head.getNext();
        while (pointer != null) {
            System.out.print(pointer.getElement() + " ");
            pointer = pointer.getNext();
        }
        System.out.println();
    }

    /**
     * 将奇数序号的元素放到一个链表，将偶数序号的元素放到一个链表，且两个新链表所占用的空间均来自原来的链表
     *
     * @return {@link ArrayList}<{@link LinkList}<{@link T}>> 返回装有两个新链表的容器
     */
    public ArrayList<LinkList<T>> divide() {

        Node<T> p = head.getNext();
        if (p == null) return null;//空链表直接返回null
        Node<T> q = p.getNext();
        Node<T> tempNode = q;
        while (q != null) {
            p.setNext(q.getNext());
            p = q.getNext();
            if (p == null) break;
            q.setNext(p.getNext());
            q = p.getNext();
        }

        ArrayList<LinkList<T>> arr = new ArrayList<>(2);
        LinkList<T> link2 = new LinkList<>();
        link2.setHead(tempNode);
        arr.add(this);
        arr.add(link2);
        return arr;
    }

    /**
     * 删除节点值为elem的节点
     *
     * @param elem 删除值为elem的节点
     */
    public void deleteNodeByElem(T elem) {
        if (head.getNext() == null)
            return;
        Node<T> pointer = head;
        while (pointer != null && pointer.getNext() != null) {
            if (pointer.getNext().getElement() == elem) {
                //删除pointer所指节点的下一个节点
                pointer.setNext(pointer.getNext().getNext());
            }
            pointer = pointer.getNext();
        }
    }
}

class TesLinkList {
    public static void main(String[] args) {
        Integer[] arr = {1};
        LinkList<Integer> links = new LinkList<>(arr);
        ArrayList<LinkList<Integer>> result = links.divide();
        result.get(0).output();
        result.get(1).output();
    }
}