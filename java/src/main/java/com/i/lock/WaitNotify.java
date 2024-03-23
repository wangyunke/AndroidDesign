package com.i.lock;

// wait、notify需要运行在同步方法synchronized中

public class WaitNotify {
    static final Object locker = new Object();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("--1111--");

        Mythread insertThread = new Mythread();
        insertThread.start();

        synchronized (locker) {
            /** wait的作用
             * 1、释放锁 2、阻塞等待 3、notify调用后，synchronized代码块执行完，重启获取锁执行
             */
            locker.wait();
        }
        System.out.println("--3333--");

    }

    public static class Mythread extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (locker) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {}

                System.out.println("--2222--");
                locker.notify();  // notify调用后，synchronized执行完之后，wait执行
                System.out.println("--synchronized end--");
            }
        }
    }

}
