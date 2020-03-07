package org.data.stucture;

public class ListNode {
    public static final ListNode EMPTY = new ListNode(0);

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "[" + val +
                "," + next +
                ']';
    }
}
