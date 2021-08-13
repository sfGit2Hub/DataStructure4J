package org.data.stucture.leetcode.easy;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//
//
//输入：s = "{[]}"
//输出：true
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串
// 👍 2560 👎 0
public class 有效的括号 {

    public static void main(String[] args) {
        String[] test = new String[]{
                "{[}]",
                "(){}",
                "([[({})]])",
                "",
                "{",
                "]"
        };

        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]  + " : " + isLegalStr(test[i]));
        }
    }

    public static boolean isLegalStr(String str){
        if (str == null || str.isEmpty() || str.length() > 10000){
            return false;
        }
        char first = str.charAt(0);
        if (isRightSymbol(first)){
            return false;
        }

        Map<Character, Character> dic = new HashMap<>();
        dic.put(')', '(');
        dic.put(']', '[');
        dic.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!isRightSymbol(cur)) {
                stack.push(cur);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                char pop = stack.pop();
                if (!dic.get(cur).equals(pop)){
                    return false;
                }
            }

        }

        return stack.isEmpty();
    }

    private static boolean isRightSymbol(char c){
        return c == ')' || c == ']' || c == '}';
    }
}
