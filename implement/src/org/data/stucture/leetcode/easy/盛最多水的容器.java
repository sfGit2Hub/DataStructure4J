package org.data.stucture.leetcode.easy;

/**
 * 双指针法
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *  n 的值至少为 2
 *
 */
public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;
        int left = height[0];
        int right = height[height.length-1];
        int maxResult = Math.min(left, right) * (height.length - 1);
        int leftIndex = 0, rightIndex = height.length-1;
        for (int i = height.length - 1; i >= 1; i--) {
            left = height[leftIndex];
            right = height[rightIndex];
            if (left <= right){
                leftIndex++;
            }
            if (left > right) {
                rightIndex--;
            }
            int temp = Math.min(left, right) * i;
            maxResult = Math.max(maxResult, temp);
        }

        return maxResult;
    }

    public static void main(String[] args) {
        盛最多水的容器 Instance = new 盛最多水的容器();
        int[] case1 = new int[]{2, 3, 5, 7, 1, 10};
        int[] case2 = new int[]{7, 3, 5, 7, 1, 4};
        int[] case3 = new int[]{11, 3, 5, 7, 1};
        int[] case4 = new int[]{2,3,4,5,18,17,6};
        System.out.println(Instance.maxArea(case1));
        System.out.println(Instance.maxArea(case2));
        System.out.println(Instance.maxArea(case3));
        System.out.println(Instance.maxArea(case4));
    }
}
