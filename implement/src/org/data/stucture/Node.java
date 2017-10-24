package org.data.stucture;

/**
 * Created by SF on 2017/10/22.
 */
public class Node<T> {
    private T element;

    public T getElement() {
        return element;
    }

    public Node setElement(T element) {
        this.element = element;
        return this;
    }
}
