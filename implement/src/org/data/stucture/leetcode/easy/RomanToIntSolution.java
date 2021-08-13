package org.data.stucture.leetcode.easy;

public class RomanToIntSolution {
    public static RomanToIntSolution Instance = new RomanToIntSolution();

    public int romanToInt(String s){
        if (s == null || s.length() < 1) return 0;
        int headNum = getIntByChar(s.charAt(0));
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            int next = getIntByChar(s.charAt(i));
            if (headNum < next){
                result -= headNum;
            } else {
                result += headNum;
            }

            headNum = next;
        }

        result += headNum;


        return result;
    }

    public static int getIntByChar(char s){
        switch (s){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public static void main(String[] args) {
        System.out.println(Instance.romanToInt("III"));
        System.out.println(Instance.romanToInt("IIV"));
        System.out.println(Instance.romanToInt("MCMXCIV"));
    }
}
