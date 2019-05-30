package com.i.designpattern.composite;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class TextFile extends File {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public void killVirus() {
        System.out.println("对文本文件[" + name + "]进行杀毒");
    }
}
