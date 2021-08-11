package org.data.stucture.leetcode;

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
// Related Topics 数学
// 👍 2987 👎 0

public class 整数反转 {

    public static void main(String[] args) {
        int x = 123;
        System.out.println("x=" + x + "-revert:" + revert(x));
        x = -123;
        System.out.println("x=" + x + "-revert:" + revert(x));
    }

    public static int revert(int x){
        if (-231 > x || x > 231 - 1){
            return 0;
        }

        byte[] resultArrays = new byte[4];
        resultArrays[0] = (byte) (x < 0 ? -1 : 1);
        x = Math.abs(x);
        resultArrays[1] = (byte) (x % 10);
        resultArrays[2] = (byte) ((x / 10) % 10);
        resultArrays[3] = (byte) ((x / 100) % 10);

        return resultArrays[0] * (resultArrays[1] * 100 + resultArrays[2] * 10 + resultArrays[3]);
    }
}
