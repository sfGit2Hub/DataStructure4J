package org.data.stucture.leetcode.easy;

/**
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class 最长公共前缀 {
    public static String[] case1 = new String[]{"flower","flow","flight"};
    public static String[] case2 = new String[]{"dog","racecar","car"};

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) return "";
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(s.length(), minLength);
        }

        int index = 0;
        boolean addTag = true;
        StringBuilder str = new StringBuilder("");
        while (index < minLength) {
            char c = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(index) != c){
                    index = minLength;
                    addTag = false;
                    break;
                }
            }
            index++;
            if (addTag) {
                str.append(c);
            }
        }

        return str.toString();
    }


    public static void main(String[] args) {
        最长公共前缀 instance  = new 最长公共前缀();
        System.out.println(instance.longestCommonPrefix(case1));
        System.out.println(instance.longestCommonPrefix(case2));
    }
}
