package com.i.leetcode;

public class Sort {

    public static void main(String[] args) {

    }

    /**
     * 冒泡排序
     */
    public void bubble(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
