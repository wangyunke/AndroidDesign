package com.i.designpattern.adapter;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class ChinaInstance {

    public void charge() {
        ChinaSocketInterface igs = new ChinaSocket();
        igs.chargeWithThree();
    }
}
