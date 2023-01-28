package DataStructure.array;

import DataStructure.dataStructureInterfaces.MyQueue;
import DataStructure.exception.CollectionEmptyException;
import DataStructure.exception.CollectionFullException;

import java.lang.reflect.Array;

/**
 * 用链表实现的队列，有一个标志位tag，以tag=false和tag=true来区分当尾指针和头指针相等时队列状态是空还是满的
 * 当入队操作成功时，置tag=true；当出队成功时置tag=false
 *
 * @author Mingxiang
 */
public class QueueWithTag<T> implements MyQueue<T> {
    private T[] arr;//用数组实现的队列
    private int head;//头指针
    private int rear;//尾指针
    private boolean tag;//标志位

    public QueueWithTag(Class<T> type, int initialCapacity) {
        arr = (T[]) Array.newInstance(type, initialCapacity);
        head = 0;
        rear = 0;
        tag = false;//初始化之后队列相当于空的状态
    }

    public boolean isEmpty() {
        return !tag && rear == head;
    }

    public boolean isFull() {
        return tag && rear == head;
    }

    public void push(T t) {
        if (isFull()) throw new CollectionFullException("队列已满，无法在队尾放入任何元素");
        arr[rear] = t;
        rear = (rear + 1) % arr.length;
        tag = true;
    }

    public int size() {
        return (rear - head + arr.length) % arr.length;
    }

    public T front() throws CollectionEmptyException {
        if (isEmpty()) throw new CollectionEmptyException("队列为空，无法取出队头元素");
        return arr[head];

    }

    public void pop() throws CollectionEmptyException {
        if (isEmpty()) throw new CollectionEmptyException("队列为空，无法弹出元素");
        head = (head + 1) % arr.length;
        tag = false;
        int arrSize = size();
        T[] tempArr = (T[]) Array.newInstance(this.arr[0].getClass(), arr.length);
        for (int i = 0; i < arrSize; i++) {
            tempArr[i] = arr[(i + head) % arr.length];
        }
        head = 0;
        rear = arrSize;
        arr = tempArr;
    }

    @Override
    public void clear() {
        rear = head = 0;
        tag = false;
        arr = (T[]) Array.newInstance(arr[0].getClass(), arr.length);
    }
}
