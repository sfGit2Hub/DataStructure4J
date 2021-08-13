package org.data.stucture.leetcode.easy;

import java.util.Arrays;

/**
 * 无重复字符的最长子串
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 */
public class LengthOfLongestSubstringSolution {
    private static LengthOfLongestSubstringSolution Instance = new LengthOfLongestSubstringSolution();

    public int lengthOfLongestSubstring(String str){
        if (str == null) return 0;
        if (str.length() == 1) return 1;
        CharsWindow charIndex = new CharsWindow();
        int maxLength = 0, startIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (charIndex.contains(current)){
                startIndex = Math.max(startIndex, charIndex.get(current));
            }
            charIndex.put(current, i + 1);
            maxLength = Math.max(maxLength, i + 1- startIndex);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String test1 = "abcabcbb";
        System.out.println(Instance.lengthOfLongestSubstring(test1));
        String test2 = "bbbbb";
        System.out.println(Instance.lengthOfLongestSubstring(test2));
        String test3 = "pwwkew";
        System.out.println(Instance.lengthOfLongestSubstring(test3));

    }

    class CharsWindow{
        //默认支持ASCII码
        //扩展ASCII码 为256个
        public int[] chars = new int[128];

        public boolean contains(char character){
            int charIndex = (int)character;
            if (charIndex > chars.length){
                chars = Arrays.copyOf(chars, charIndex);
                return false;
            }

            return chars[charIndex] > 0;
        }

        public void put(char character, int index) {
            int charIndex = (int)character;
            if (charIndex > chars.length){
                chars = Arrays.copyOf(chars, charIndex);
            }
            chars[charIndex] = index;

        }

        public int get(Character character) {
            int charIndex = (int)character;
            if (charIndex > chars.length) {
                return -1;
            }

            return chars[character];
        }
    }
}
