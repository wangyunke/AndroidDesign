package com.i.designpattern.composite;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class ImageFile extends File {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    public void killVirus() {
        System.out.println("对图像文件[" + name + "]进行杀毒");
    }
}
