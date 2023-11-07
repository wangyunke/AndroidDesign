package com.i.java;

public class Code {
//    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//    输入: s = "pwpkew"
//    输出: 3
//    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//    请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//    输入: s = "bbbbb"
//    输出: 1
//    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。


    public static void main(String[] args) {
        lengthOfLongestSubstring("dvdf");

    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 0;
        StringBuilder builder = new StringBuilder();

        for (char val: chars) {
            int index= builder.indexOf(val+"");
            if (index<0) {
                builder.append(val);
            } else {
                maxLen = Math.max(maxLen, builder.length());

                String tmp=builder.substring(index+1, builder.length());
                System.out.println(tmp);
                builder = new StringBuilder();
                builder.append(tmp).append(val);
            }
        }
        maxLen = Math.max(maxLen, builder.length());
        System.out.println(maxLen);
        return maxLen;
    }
}
