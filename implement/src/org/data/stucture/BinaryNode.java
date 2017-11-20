package org.data.stucture;

/**
 * Created by Administrator on 2017/11/20.
 */
public class BinaryNode<T> {
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(T element, BinaryNode left, BinaryNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T element){
        this(element, null, null);
    }
}
