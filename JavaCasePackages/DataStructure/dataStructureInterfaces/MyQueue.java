package DataStructure.dataStructureInterfaces;

import DataStructure.exception.CollectionEmptyException;

public interface MyQueue<T> {
    void push(T element);

    void pop() throws CollectionEmptyException;

    int size();

    T front() throws CollectionEmptyException;

    boolean isEmpty();

    void clear();
}
