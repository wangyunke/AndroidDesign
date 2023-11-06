package com.i.leetcode;

import jdk.internal.org.jline.utils.Log;

public class ArrToQueue {

    static class Queue {
        int[] arr;
        int maxSize;
        int lastIndex = 0;
        int frontIndex = 0;

        Queue(int maxSize) {
            arr = new int[maxSize];
        }

        void enQueue(int val) {
            if (isFull()) {
                Log.error("queue full");
            } else {
                arr[lastIndex++] = val;
            }
        }

        int deQueue() {
            if (isEmpty()) {
                Log.error("queue empty");
                throw new RuntimeException("queue empty");
            } else {
                return arr[frontIndex++];
            }
        }

        void show() {
            for (int i = frontIndex; i <= lastIndex; i++) {
                Log.info(arr[frontIndex]);
            }
        }

        int size() {
            int diff = lastIndex - frontIndex;
            return Math.max(diff, 0);
        }

        boolean isEmpty() {
            return frontIndex > lastIndex;
        }

        boolean isFull() {
            return lastIndex > maxSize - 1;
        }
    }

}
