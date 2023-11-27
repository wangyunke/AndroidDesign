package com.i.lc;

public class Revert {

    public static void main(String[] args) {
        String value = "my hello from world";

//        String result = revertEasy(value);
//        System.out.println("result=" + result);

//        String result = revertEasy2(value);
//        System.out.println("result=" + result);

//        String result = revertQi(value);
//        System.out.println("result=" + result);

        int[] arr = {3,0,1};
        int res=missingNumber(arr);
        System.out.println("result=" + res);
    }

    public static int missingNumber(int[] nums) {
        if(nums==null){
            return 0;
        }
        int len=nums.length;
        int sum=((len)*(len+1))/2;

        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public static String revertQi(String value) {
        String[] chars = value.split(" ");
        int len = chars.length;
        for (int i = 0; i < len/2; i++) {
            int pos = len - i - 1;
            String tmp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (String ch : chars) {
            sb.append(ch).append(" ");
        }
        return sb.toString().trim();
    }

    public static String revertDifficult(String value) {
        char[] chars = value.toCharArray();
        char[] result = swap(chars, 0, chars.length - 1);
        System.out.println("chars=" + String.valueOf(result)); //"dlrow morf olleh ym"

        int start = 0, end = 0;
        int len = chars.length;
        while (start < len) {
            while (end < len && chars[end] != ' ') {
                end++;
            }
            char[] single = swap(chars, start, end - 1);
            System.out.println("single=" + String.valueOf(single));

            start = end + 1;
            end++;
        }
        return String.valueOf(chars);
    }

    public static char[] swap(char[] chars, int left, int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
        return chars;
    }

    public static String revertEasy(String value) {
        String[] chars = value.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String revertEasy2(String value) {
        String[] chars = value.split(" ");
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            String tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
        return String.join(" ", chars);
    }

}
