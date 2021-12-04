package com.i.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) {
        exec();
    }

    private static void exec() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 7,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue(2),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("RejectedExecutionHandler wangyunke- = " + r);
                    }
                });

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    System.out.println("wangyunke--run = " + index);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            executor.execute(run);
        }
    }
}