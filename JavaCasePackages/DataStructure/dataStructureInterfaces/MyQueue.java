package dataStructureInterfaces;

public interface MyQueue<T> {
    void push(T element);

    void pop();

    int size();

    T front();

    boolean isEmpty();

    void clear();
}
