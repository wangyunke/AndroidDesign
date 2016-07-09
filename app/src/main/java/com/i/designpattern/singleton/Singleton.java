package com.i.designpattern.singleton;

/**
 * Created by ykw on 2016/6/23.
 */
public class Singleton {
    private static Singleton sInstance = new Singleton();

    public static Singleton getInstance() {
        return sInstance;
    }

    private Singleton() {
    }
}
