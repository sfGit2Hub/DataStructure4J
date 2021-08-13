package org.data.stucture.leetcode.easy;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 输入: 123
 * 输出: 321
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class ReverseSolution {
    public static ReverseSolution Instance = new ReverseSolution();
    public static int Max = (1 << 31) - 1;
    public static int Min = -(Max + 1);

    public int reverse(int num) {
        if (num == 0) return num;
        int index = 0;
        byte[] numList = new byte[32];
        while (num != 0) {
            byte ceil = (byte) (num % 10);
            numList[index] = ceil;
            num = num / 10;
            index++;
        }

        long result = 0;
        for (int i = 0; i < index; i++) {
            result += Math.pow(10, index - i - 1) * numList[i];
        }
        if (result > Max || result < Min) return 0;
        return (int) result;
    }


    public int reverseV2(int num) {
        if (num == 0) {
            return 0;
        }

        int recev = 0;
        while (num != 0) {
            int pop = num % 10;
            if (recev > Integer.MAX_VALUE / 10 || (recev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (recev < Integer.MIN_VALUE / 10 || (recev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            recev = recev * 10 + pop;
            num /= 10;
        }

        return recev;
    }

    public static void main(String[] args) {
        System.out.println(Instance.reverse(123));
        System.out.println(Instance.reverse(120));
        System.out.println(Instance.reverse(-123));
        System.out.println(Instance.reverse(1534236469));
    }
}
