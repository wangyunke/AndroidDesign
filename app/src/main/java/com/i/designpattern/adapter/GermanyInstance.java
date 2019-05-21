package com.i.designpattern.adapter;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class GermanyInstance {

    public void charge() {
        GermanySocketInterface igs = new GermanySocket();
        igs.chargeWithTwo();
    }
}
