package com.i.designpattern.lock;

/**
 * Created by ykw on 2016/9/4.
 */
public class Entity1 implements ICommond {

    void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                iLock.lock();
                System.out.println("1111111111111");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    iLock.unlock();
//                    iConditionVariable.open();
                }
            }
        }).start();
    }
}
