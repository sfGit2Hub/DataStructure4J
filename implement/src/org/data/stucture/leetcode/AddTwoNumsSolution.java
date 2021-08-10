package org.data.stucture.leetcode;


import org.data.stucture.ListNode;

public class AddTwoNumsSolution {
    public ListNode addTwoNumbers(ListNode num1, ListNode num2) {
        if (num1 == null) {
            return num2;
        }

        if (num2 == null) {
            return num1;
        }


        Ref refUp = new Ref();
        ListNode header = new ListNode(0);
        ListNode tempResult = header;
        while (num1 != null || num2 != null) {
            tempResult.next = addTwoNumNode(num1, num2, refUp);
            tempResult = tempResult.next;
            if (num1 != null) {
                num1 = num1.next;
            }
            if (num2 != null) {
                num2 = num2.next;
            }

        }

        if (refUp.upResult > 0) {
            tempResult.next = new ListNode(refUp.upResult);
        }

        return header.next;
    }

    private ListNode addTwoNumNode(ListNode num1, ListNode num2, Ref up) {
        if (num1 == null && num2 == null) {
            return null;
        }
        int result = 0, temp = 0;
        if (num1 != null && num2 != null) {
            temp = num1.val + num2.val + up.upResult;
        } else {
            if (num1 != null) {
                temp = num1.val + up.upResult;
            }
            if (num2 != null) {
                temp = num2.val + up.upResult;
            }
        }

        if (temp >= 10) {
            result = temp - 10;
            up.upResult = 1;
        } else {
            result = temp;
            up.upResult = 0;
        }

        return new ListNode(result);
    }

    class Ref {
        int upResult;
    }

    public static void main(String[] args) {
        ListNode num1 = buildListNode(1,2,3);
        ListNode num2 = buildListNode(9,2,7);
        ListNode result = new AddTwoNumsSolution().addTwoNumbers(num1, num2);
        System.out.println(result);
    }

    private static ListNode buildListNode(int ...nums) {
        if (nums == null || nums.length < 1) return ListNode.EMPTY;
        ListNode head = new ListNode(nums[0]);
        ListNode node = head;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return head;
    }
}
