package DataStructure.linklist.queueByLinkList;

import DataStructure.array.QueueWithTag;
import DataStructure.exception.CollectionEmptyException;

public class Tester {
    public static void main(String[] args) {
        testQueueWithRear();
    }

    public static void testQueueWithRear() {
        QueueWithRear<Integer> list = new QueueWithRear<>();
        list.push(12);
        list.push(41);
        list.push(6253);
        list.push(523);
        Integer data = list.front();
        System.out.println(data);
        list.pop();
        list.pop();
        System.out.println(list.front());
        list.clear();
        try {
            System.out.println(list.front());
        } catch (CollectionEmptyException e) {
            System.out.println(e.getCause().getMessage());
        }

    }

    public static void testQueueWithTag() {
        QueueWithTag<Integer> list = new QueueWithTag<>(Integer.class, 5);
        list.push(12);
        list.push(41);
        list.push(6253);
        list.push(523);
        Integer data = list.front();
        System.out.println(data);
        list.pop();
        System.out.println(list.front());
    }

}
