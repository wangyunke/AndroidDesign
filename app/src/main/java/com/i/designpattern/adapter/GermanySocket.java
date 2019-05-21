package com.i.designpattern.adapter;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class GermanySocket implements GermanySocketInterface {
    @Override
    public void chargeWithTwo() {
        System.out.println("充电用两个角");
    }
}
