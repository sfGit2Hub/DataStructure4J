package org.data.stucture.leetcode;

import java.util.Arrays;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表
// 👍 1841 👎 0
public class 合并两个有序链表 {

    public static void main(String[] args) {
        short[] l1 = new short[]{1,2,4}, l2 = new short[]{1,3,4};
        System.out.println(Arrays.toString(combineArray(l1, l2)));

        l1 = new short[]{};
        l2 = new short[]{};
        System.out.println(Arrays.toString(combineArray(l1, l2)));

        l1 = new short[]{};
        l2 = new short[]{0};
        System.out.println(Arrays.toString(combineArray(l1, l2)));

        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        System.out.println(mergeTwoLists(a, b));

    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder("[");
            str.append(val);
            ListNode cur = next;
            while (cur != null){
                str.append(",").append(cur.val);
                cur = cur.next;
            }
            str.append("]");
            return str.toString();
        }
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = new ListNode();
        if (l1.val < l2.val){
            head.val = l1.val;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head.val = l2.val;
            head.next = mergeTwoLists(l1, l2.next);
        }

        return head;
    }

    public static short[] combineArray(short[] a, short[] b){
        if (a == null || a.length < 1){
            return b;
        }
        if (b == null || b.length < 1){
            return a;
        }
        short[] result = new short[a.length + b.length];
        int index = 0;
        int indexA = 0, indexB = 0;
        while (index < a.length + b.length){
            if (indexB >= b.length || a[indexA] < b[indexB]){
                result[index] = a[indexA];
                indexA++;
            } else {
                result[index] = b[indexB];
                indexB++;
            }
            index++;
        }

        return result;
    }
}
