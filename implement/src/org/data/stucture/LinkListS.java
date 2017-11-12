package org.data.stucture;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/11/5.
 */
public class LinkListS<T> implements Iterable<T>{
    private Node<T> beginMarker;
    private Node<T> endMarker;
    private int theSize;
    private int modCount;

    public LinkListS() {
        this.doClear();
    }

    public void clear() {
        this.doClear();
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int idx) {
        return this.getNode(idx).data;
    }

    public void set(int idx, T ele) {
        Node<T> old = getNode(idx);
        old.data = ele;
    }

    /**
     * 查询链表中元素是否存在，如果是自定义对象，需要重写对象的equals方法
     * @param ele
     * @return
     */
    public boolean contain(T ele) {
        return this.indexOf(ele) != -1;
    }

    /**
     * 查询链表中元素的位置，返回第一个元素的索引值
     * 如果是对象，需要重写对象的equals方法
     * @param ele
     * @return
     */
    public int indexOf(T ele) {
        if (ele == null) {
            throw new IllegalArgumentException("the query element should not be null");
        }
        Node<T> prev = beginMarker.next;
        int index = 0;
        while (!ele.equals(prev.data)) {
            prev = prev.next;
            index++;
            if (prev == null) {
                index = -1;
            }
        }
        return index;
    }

    public T remove(int idx) {
        Node<T> old = getNode(idx);
        return this.remove(old);
    }

    private T remove(Node<T> old) {
        old.prev.next = old.next;
        old.next.prev = old.prev;
        theSize--;
        modCount++;
        return old.data;
    }

    private T addBefore(Node<T> current, T ele) {
        Node<T> newPrev = new Node<T>(ele, current.prev, current);
        current.prev = newPrev;
        newPrev.prev.next = newPrev;
        theSize++;
        modCount++;
        return ele;
    }

    public T add(T ele) {
        return this.add(this.size(), ele);
    }

    /**
     * 前插法插入元素
     * @param size  插入的索引
     * @param ele   插入的元素
     * @return
     */
    public T add(int size, T ele) {
        return addBefore(this.getNode(size), ele);
    }

    private Node<T> getNode(int idx) {
        return this.getNode(idx, 0, this.size());
    }

    private Node<T> getNode(int index, int lower, int upper) {
        Node<T> the;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException("get node index is out of bounds");
        }
        if (index < this.size() / 2) {
            /**
             * 用前插法插入元素，所以在查找的时候，从beginMarker 节点的 next 开始查询
             */
            the = beginMarker.next;
            for (int i = 0; i < index; i++) {
                the = the.next;
            }
        } else {
            the = endMarker;
            for (int i = this.size(); i > index; i--) {
                the = the.prev;
            }
        }
        return the;
    }

    private void doClear() {
        beginMarker = new Node<T>(null ,null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkListIterator();
    }

    /**
     * Created by SF on 2017/10/22.
     */
    private static class Node<T> {
        private Node<T> prev;
        private T data;
        private Node<T> next;
        private int index;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    @SuppressWarnings("unchecked")
    private class LinkListIterator implements Iterator<T> {
        private Node<T> current = beginMarker.next;
        //在迭代操作中不能做 链表的更改
        private int expectedModCount = modCount;
        private boolean canBeMove = false;


        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T ele = current.data;
            current = current.next;
            canBeMove = true;
            return ele;
        }

        @Override
        public void remove() {
            if (!canBeMove) {
                throw new IllegalStateException("current element can be removed!");
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            LinkListS.this.remove(current.prev);
            expectedModCount++;
            canBeMove = false;
        }

        @Override
        /**
         * 允许 Java8 Lamuda 表达式 forEach循环
         */
        public void forEachRemaining(Consumer<? super T> action) {

        }
    }
}
