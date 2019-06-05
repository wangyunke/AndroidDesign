package com.i.designpattern.singleton;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class UseInstance {

    public static void use() {
        SingletonHunger.getInstance().function();
        SingletonLazy.getInstance().function();
        SingletonDoubleValid.getInstance().function();
        SingletonStaticInnerClass.getInstance().function();
        SingletonEnum.INSTANCE.function();
    }
}
