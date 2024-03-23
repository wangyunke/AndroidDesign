package com.i.print;

import java.util.concurrent.CountDownLatch;

public class JoinLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("--1111--");

        Mythread insertThread = new Mythread();
        insertThread.start();

//        insertThread.join(); //方法一
        countDownLatch.await(); //方法二

        System.out.println("--3333--");
    }

    public static class Mythread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {}
            System.out.println("--2222--");

            countDownLatch.countDown(); //方法二
        }
    }

}
