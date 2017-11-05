package org.data.stucture;

/**
 * Created by Administrator on 2017/11/5.
 */
public class LinkListS<T> {
    private int size;
    private Node<T>[] nodes;

    public LinkListS() {
        this.nodes = new Node[10];
        this.size = 0;
    }

    public LinkListS(T[] elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("Error empty elements");
        }
        this.nodes = new Node[elements.length];
        this.size = elements.length;
        for (int i=0; i<elements.length; i++) {
            this.nodes[i].setElement(elements[i]).setIndex(i);
            if (i != elements.length-1) {
                this.nodes[i].setNext(this.nodes[i++]);
            }
        }
    }

    public T get(int idx) {
        if (idx > this.size || idx < 0) {
            throw new IllegalArgumentException("Out of Index!");
        }
        return this.nodes[idx].getElement();
    }

    /**
     * 返回旧数据
     * @param idx
     * @param ele
     * @return
     */
    public T set(int idx, T ele) {
        if (idx < 0 || idx > this.size) {
            throw new IllegalArgumentException("The index is Error");
        }
        T old = this.nodes[idx].getElement();
        this.nodes[idx].setElement(ele);
        return old;
    }

    public boolean add(T ele) {
        add(size(), ele);
        return true;
    }

    public void add(int idx, T ele) {
        if (idx < 0 || idx > size()) {
            throw new IllegalArgumentException("Add element Out of index!");
        }
        if (this.nodes.length == size()) {
            ensureCapacity(size() * 2 +1);
        }
        for (int i=this.size; i > idx; i--) {
            this.nodes[i] = this.nodes[i--];
        }
        this.nodes[idx].setElement(ele);
        this.size++;
    }

    public T remove(int idx) {
        if (idx < 0 || idx > size()) {
            throw new IllegalArgumentException("delete element Out of index!");
        }
        Node<T> removeItem = this.nodes[idx];
        for (int i=idx; i< size(); i++) {
            this.nodes[i] = this.nodes[i+1];
        }
        this.size--;
        return removeItem.getElement();
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < this.size)
            return;
        Node<T>[] olds = this.nodes;
        this.nodes = new Node[newCapacity];
        for (int i = 0; i < olds.length; i++) {
            this.nodes[i].setElement(olds[i].getElement());
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
}
