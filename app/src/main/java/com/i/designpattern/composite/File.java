package com.i.designpattern.composite;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public abstract class File {

    public void add(File f) {
        System.out.println("对不起，不支持该方法！");
    }

    abstract void killVirus();
}
