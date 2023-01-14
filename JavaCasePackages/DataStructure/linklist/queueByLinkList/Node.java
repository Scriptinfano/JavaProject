package DataStructure.linklist.queueByLinkList;

public class Node<T> {
    private T element;
    private Node<T> next;

    public Node(T element, Node<T> theNext) {
        this.element = element;
        this.next = theNext;
    }

    public Node(Node<T> theNext) {
        this.next = theNext;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> theNext) {
        this.next = theNext;
    }
}
