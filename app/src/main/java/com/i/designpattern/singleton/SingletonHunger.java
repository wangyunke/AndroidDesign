package com.i.designpattern.singleton;

/**
 * Created by ykw on 2016/6/23.
 */
public class SingletonHunger {
    private SingletonHunger() {
    }

    private static final SingletonHunger sInstance = new SingletonHunger();

    public static SingletonHunger getInstance() {
        return sInstance;
    }

    public void function(){
        System.out.println("饿汉式");
    }
}
