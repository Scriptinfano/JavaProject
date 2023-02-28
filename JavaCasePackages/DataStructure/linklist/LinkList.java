package DataStructure.linklist;

import DataStructure.linklist.queueByLinkList.Node;

import java.util.ArrayList;

public class LinkList<T> {
    private Node<T> head;

    public void insert(T[] arr) {
        Node<T> newNode = new Node<>(arr[0]);
        Node<T> pointer = newNode;
        for (int i = 1; i < arr.length; i++) {
            pointer.setNext(new Node<>(arr[i]));
            pointer = pointer.getNext();
        }
        head = newNode;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void output() {
        Node<T> pointer = head;
        while (pointer != null) {
            System.out.print(pointer.getElement() + " ");
            pointer = pointer.getNext();
        }
        System.out.println();
    }

    /**
     * 将奇数序号的元素放到一个链表，将偶数序号的元素放到一个链表，且两个新链表的元素均来自原来的链表
     *
     * @return {@link ArrayList}<{@link LinkList}<{@link T}>> 返回装有两个新链表的容器
     */
    public ArrayList<LinkList<T>> divide() {

        Node<T> p = head;
        if (p == null) return null;
        Node<T> re = head, re2 = head.getNext();
        Node<T> q = head.getNext();
        while (q != null) {
            p.setNext(q.getNext());
            p = q.getNext();
            if (p == null) {
                q.setNext(null);
                break;
            }
            q.setNext(p.getNext());
            q = p.getNext();
        }
        if (p != null) p.setNext(null);

        ArrayList<LinkList<T>> arr = new ArrayList<>(2);
        LinkList<T> link1 = new LinkList<>();
        link1.setHead(re);
        LinkList<T> link2 = new LinkList<>();
        link2.setHead(re2);
        arr.add(link1);
        arr.add(link2);

        return arr;
    }
}

class TesLinkList {
    public static void main(String[] args) {
        LinkList<Integer> links = new LinkList<>();
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        links.insert(arr);
        ArrayList<LinkList<Integer>> result = links.divide();
        result.get(0).output();
        result.get(1).output();
    }
}