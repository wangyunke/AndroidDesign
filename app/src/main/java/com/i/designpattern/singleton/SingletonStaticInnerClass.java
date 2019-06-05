package com.i.designpattern.singleton;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class SingletonStaticInnerClass {
    private SingletonStaticInnerClass() {
    }

    private static class Holder {
        private static final SingletonStaticInnerClass sInstance = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance() {
        return Holder.sInstance;
    }

    public void function() {
        System.out.println("静态内部类");
    }

}
