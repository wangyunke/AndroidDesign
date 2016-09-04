package com.i.designpattern.lock;

/**
 * Created by ykw on 2016/9/4.
 */
public class Entity2 implements ICommond {

    void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                iLock.lock();
//                iConditionVariable.block();
                System.out.println("2222222222222");
//                iConditionVariable.open();
                iLock.unlock();
            }
        }).start();
    }

}
