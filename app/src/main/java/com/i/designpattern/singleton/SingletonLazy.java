package com.i.designpattern.singleton;

/**
 * Created by ykw on 2016/6/24.
 */
public class SingletonLazy {
    private SingletonLazy() {
    }

    private static SingletonLazy sInstance;

    public synchronized static SingletonLazy getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonLazy();
        }
        return sInstance;
    }

    public void function() {
        System.out.println("懒汉式");
    }
}
