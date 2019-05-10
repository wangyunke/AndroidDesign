package com.i.designpattern.templet;

/**
 * Copyright (C) 2019, Xiaomi Inc. All rights reserved.
 */
public class UseCarInstance {

    public static void useCar() {
        Car audiCar = new AudiCar();
        audiCar.drive();

        VolvoCar volvoCar = new VolvoCar();
        volvoCar.drive();
    }

}
