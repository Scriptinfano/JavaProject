package linklist.queueByLinkList;

import dataStructureInterfaces.MyQueue;
import exception.CollectionEmptyException;

/**
 * 链表实现的队列，仅有尾指针
 *
 * @author Mingxiang
 * @date 2022/10/04
 */
public class QueueWithRear<T> implements MyQueue<T> {
    private Node<T> rear;

    public QueueWithRear() {
        Node<T> head = new Node<>(null);
        head.setNext(head);
        rear = head;//链表为空时，尾指针指向头节点
    }

    public boolean isEmpty() {
        return rear.getNext() == rear;
    }

    /**
     * 入队
     *
     * @param data 新节点的数据
     */
    public void push(T data) {
        if (isEmpty()) {
            rear.setNext(new Node<>(data, rear));
            rear = rear.getNext();
            return;
        }
        Node<T> newNode = new Node<>(data, rear.getNext().getNext());
        rear.getNext().setNext(newNode);
    }

    /**
     * 出队
     */
    public void pop() {
        if (isEmpty()) throw new CollectionEmptyException();
        Node<T> p = rear;
        while (!p.getNext().equals(rear)) {
            p = p.getNext();
        }

        rear = p;
    }

    public T front() {
        if (isEmpty()) throw new CollectionEmptyException();
        return rear.getElement();
    }

    @Override
    public int size() {
        int size = 0;
        Node<T> p = rear.getNext();
        while (!p.equals(rear)) {
            p = p.getNext();
            size++;
        }
        return size;
    }

    @Override
    public void clear() {
        if (isEmpty()) return;
        Node<T> p = rear.getNext();
        p.setNext(p);
        rear = p;
    }
}
