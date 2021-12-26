package com.i.java;

import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {

    public static void main(String[] args) {
        System.out.println("线程 main 正在 执行。。" + Thread.currentThread().getName());

        Timer timer = new Timer();
        timer.schedule(new OneTask(1), 5000);// 5秒后启动任务
    }

    public static class OneTask extends TimerTask {

        private int id;

        public OneTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + ":  正在 执行。。" + Thread.currentThread().getName());
            //System.gc();
        }
    }
}
