package com.i.designpattern.singleton;

/**
 * Created by ykw on 2016/7/13.
 */
public class SingletonDoubleValid {
    private SingletonDoubleValid() {
    }

    private volatile  static SingletonDoubleValid sInstance;

    public static SingletonDoubleValid getInstance() {
        if(sInstance==null){
            synchronized (SingletonDoubleValid.class){
                if(sInstance==null) {
                    sInstance = new SingletonDoubleValid();
                }
            }
        }
        return sInstance;
    }
}
