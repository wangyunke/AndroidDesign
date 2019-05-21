package com.i.designpattern.adapter;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class ChinaSocket implements ChinaSocketInterface {
    @Override
    public void chargeWithThree() {
        System.out.println("充电用三个角");
    }
}
