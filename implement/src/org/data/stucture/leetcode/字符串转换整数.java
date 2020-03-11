package org.data.stucture.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 输入: "42"
 * 输出: 42
 *
 * 输入: "   -42"
 * 输出: -42
 */
public class 字符串转换整数 {
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) return 0;
        int result = 0, startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) continue;
            startIndex = i;
            break;
        }
        boolean nativeSign = false, isGetNum = false, hasSign = false;
        for (int i = startIndex; i < str.length(); i++) {
            int num = getNumByChar(str.charAt(i));
            if (!isSignChar(str.charAt(i)) && num < 0) break;
            if (!isGetNum && !hasSign && isSignChar(str.charAt(i))){
                if (str.charAt(i) == '-') {
                    nativeSign = true;
                }
                hasSign = true;
                continue;
            }
            //遇到非法字符返回
            if (num < 0){
                break;
            }
            isGetNum = true;
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && num > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            result *= 10;
            if (nativeSign) {
                result -= num;
            }
            else {
                result += num;
            }
        }

        return result;
    }

    public static boolean isSignChar(char signChar){
        return signChar == '-' || signChar == '+';
    }

    public static int getNumByChar(char numChar) {
        switch (numChar){
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        字符串转换整数 Instance = new 字符串转换整数();
        String case1 = " -42";
        String case2 = "    42";
        String case3 = "words and 987";
        String case4 = "-91283472332";
        String case5 = "+1";
        String case6 = "+-2";
        System.out.println(case1 + ":" + Instance.myAtoi(case1) + (Instance.myAtoi(case1) == -42));
        System.out.println(case2 + ":" + Instance.myAtoi(case2) + (Instance.myAtoi(case2) == 42));
        System.out.println(case3 + ":" + Instance.myAtoi(case3) + (Instance.myAtoi(case3) == 0));
        System.out.println(case4 + ":" + Instance.myAtoi(case4) + (Instance.myAtoi(case4) == -2147483648));
        System.out.println(case5 + ":" + Instance.myAtoi(case5) + (Instance.myAtoi(case5) == 1));
        System.out.println(case6 + ":" + Instance.myAtoi(case6) + (Instance.myAtoi(case6) == 0));
    }
}
