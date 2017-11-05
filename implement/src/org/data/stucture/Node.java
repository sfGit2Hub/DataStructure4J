package org.data.stucture;

/**
 * Created by SF on 2017/10/22.
 */
public class Node<T> {
    private T element;
    private int index;

    private Node<T> next;

    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    public Node(T element, Node next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public Node setElement(T element) {
        this.element = element;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public Node setIndex(int index) {
        this.index = index;
        return this;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node setNext(Node<T> next) {
        this.next = next;
        return this;
    }
}
