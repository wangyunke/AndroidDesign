package com.i.lc;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
//        lengthOfLongestSubstring("dvdf");

        int[] sums={2,9,0,5,3,1,4};
        sortEvenOdd(sums);
        for (int val : sums) {
            System.out.print(val+",");
        }
    }

    //给定一个数组，使其中奇数排在偶数前面
    static int[] sortEvenOdd(int[] sums) {
        if (sums == null || sums.length == 0) {
            return null;
        }
        int start = 0;
        int end = sums.length - 1;
        int startVal;
        int endVal;

        while (start < end) {
            startVal = sums[start];
            endVal = sums[end];

            while (!isEvenNum(startVal) && start < end) {
                start++;
                startVal = sums[start];
            }
            while (isEvenNum(endVal) && start < end) {
                end--;
                endVal = sums[end];
            }

            if (start < end) {
                int tmp = sums[start];
                sums[start] = sums[end];
                sums[end] = tmp;

                start++;
                end--;
            }
        }
        return sums;
    }

    static boolean isEvenNum(int val) {
        return val % 2 == 0;
    }


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
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 0;
        StringBuilder builder = new StringBuilder();

        for (char val : chars) {
            int index = builder.indexOf(val + "");
            if (index < 0) {
                builder.append(val);
            } else {
                maxLen = Math.max(maxLen, builder.length());

                String tmp = builder.substring(index + 1, builder.length());
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
