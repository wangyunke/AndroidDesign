package com.i.designpattern.templet;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public abstract class Car {

   final public void drive(){
        this.start();
        this.stop();
    }

    protected abstract void start();

    protected abstract void stop();

}
