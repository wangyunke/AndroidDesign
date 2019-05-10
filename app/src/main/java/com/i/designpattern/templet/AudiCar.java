package com.i.designpattern.templet;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class AudiCar extends Car {

    @Override
    protected void start() {
        System.out.println("my audi q3 start");
    }

    @Override
    protected void stop() {
        System.out.println("my audi q3 stop");
    }

}
