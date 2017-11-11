package org.data.stucture;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Administrator on 2017/11/7.
 */
public class ArrayListS<T> implements Iterable<T>{
    private int size;
    private Object[] nodes;

    public ArrayListS() {
        this.nodes = new Object[10];
        this.size = 0;
    }

    public ArrayListS(T[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Error empty elements");
        }
        this.nodes = new Object[elements.length];
        this.size = elements.length;
        for (int i=0; i<elements.length; i++) {
            this.nodes[i] = elements[i];
            if (i != elements.length-1) {
                this.nodes[i]= this.nodes[i++];
            }
        }
    }

    public T get(int idx) {
        if (idx > this.size || idx < 0) {
            throw new IllegalArgumentException("Out of Index!");
        }
        return (T) this.nodes[idx];
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
        T old = (T) this.nodes[idx];
        this.nodes[idx] = ele;
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
        this.nodes[idx] = ele;
        this.size++;
    }

    public T remove(int idx) {
        if (idx < 0 || idx > size()) {
            throw new IllegalArgumentException("delete element Out of index!");
        }
        T removeItem = (T) this.nodes[idx];
        for (int i=idx; i< size(); i++) {
            this.nodes[i] = this.nodes[i+1];
        }
        this.size--;
        return removeItem;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < this.size)
            return;
        T[] olds = (T[]) this.nodes;
        this.nodes = new Object[newCapacity];
        for (int i = 0; i < olds.length; i++) {
            this.nodes[i] = olds[i];
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListIteratorS();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    private class ArrayListIteratorS implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) ArrayListS.this.nodes[current++];
        }

        @Override
        public void remove() {
            ArrayListS.this.remove(--current);
        }
    }
}
