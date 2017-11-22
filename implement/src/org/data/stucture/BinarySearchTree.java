package org.data.stucture;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/11/20.
 */
public class BinarySearchTree<T extends Comparable> {
    private static class BinaryNode<T>{
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public BinaryNode(T element){
            this(element, null, null);
        }
    }

    private BinaryNode<T> root;
    private Comparator<? super T> comparator;   //自定义比较函数

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Comparator<? super T> cmp) {
        this.comparator = cmp;
        this.root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contain(T x) {
        return contain(x, root);
    }

    public T findMin() {
        if (isEmpty())
            throw new IllegalStateException("The tree is empty");
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty())
            throw new IllegalStateException("The tree is empty");
        return findMax(root).element;
    }

    public T insert(T ele) {
        return insert(ele, root).element;
    }

    public T remove(T ele) {
        return remove(ele, root).element;
    }

    private BinaryNode<T> insert(T ele, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<T>(ele , null, null);
        }
        int compareResult = comparator == null ?
                ele.compareTo(node.element) : comparator.compare(ele, node.element);
        if (compareResult < 0)
            node.left = insert(ele, node.left);
        else if (compareResult > 0)
            node.right = insert(ele, node.right);
        else ;
        return node;
    }

    private BinaryNode<T> remove(T ele, BinaryNode<T> node) {
        if (node == null) {
            return node;
        }
        int compareResult = comparator == null ?
                ele.compareTo(node.element) : comparator.compare(ele, node.element);
        if (compareResult < 0) {
            node.left = remove(ele, node);
        } else if (compareResult > 0) {
            node.right = remove(ele, node);
        } else if (node.left != null && node.right != null) {
//            双子树的节点
            node.element = findMin(node).element;
            node.right = remove(ele, node.right);
        }else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node != null) {
            while (node.right != null)
//                该方法中传入的是对象的引用，可以重新赋值，不会对原数据结构造成改变
                node = node.right;
        }
        return node;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node.left != null)
            return findMin(node.left);
        else
            return node;
    }

    private boolean contain(T ele, BinaryNode<T> node) {
        if (ele == null) {
            throw new IllegalArgumentException("contain method can't compare null obj!");
        }
        if (node == null)
            return false;

        int compareResult = comparator == null ?
                ele.compareTo(node.element) : comparator.compare(ele, node.element);

        if (compareResult < 0)
            return contain(ele, node.left);
        if (compareResult > 0)
            return contain(ele, node.right);
        else
            return true;
    }
}
