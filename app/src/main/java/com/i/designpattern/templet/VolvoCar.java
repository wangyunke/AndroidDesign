package com.i.designpattern.templet;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class VolvoCar extends Car {

    @Override
    protected void start() {
        System.out.println("my volvo xc40 start");
    }

    @Override
    protected void stop() {
        System.out.println("my volvo xc40 stop");
    }
}
